package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.algorithms2_3.{Direction, LeftDirection, RightDirection}
import com.google.common.annotations.VisibleForTesting

import scala.language.postfixOps

/**
  * Ex 2.4.24 Priority queue with explicit links. Implement a priority queue using a heap-ordered binary
  * tree, but use a triply linked structure instead of an array. You will need three links per greaterNode:
  * two to traverse down the tree and one to traverse up the tree. Your implementation should guarantee
  * logarithmic running time per operation, even if no maximum priority-queue size is known ahead time.
  */
class LinkedPQ[Key](implicit override protected val cmp: Ordering[_ >: Key])
  extends MaxPQ[Key] with PQNodeSupport[Key] {

  // also is the maxNode
  private var root: Option[Node] = None
  private var last: Option[Node] = None

  case class GreaterSmallerNodes(greater: Node, smaller: Node)

  override def insert(value: Key): Unit = {
    N += 1
    root = Some(insertHelper(root, None, value))
    root = Some(swim(last.get))
  }

  /**
    * The purpose of this helper method is to recursively look
    * for a leaf on the shortest path while maintaining the existing
    * tree structure
    *
    * @param maybeNextNode - next node on the path
    * @param value
    * @return current tree with new greaterNode appended as per described above
    */
  @VisibleForTesting
  protected def insertHelper(maybeNextNode: Option[Node], maybeParentNode: Option[Node], value: Key): Node = maybeNextNode match {
    case None =>
      last = Some(Node(value = value, parent = maybeParentNode))
      last.orNull
    case Some(nextNode) =>
      // compare left and right sizes to see where to go
      val leftSize = nextNode.left.map(_.size()).getOrElse(0)
      val rightSize = nextNode.right.map(_.size()).getOrElse(0)

      val result = if (leftSize <= rightSize) {
        val insertedNode = insertHelper(nextNode.left, maybeNextNode, value)
        nextNode.copy(left = Some(insertedNode.copy(parent = Some(nextNode))))
      } else {
        val insertedNode = insertHelper(nextNode.right, maybeNextNode, value)
        nextNode.copy(right = Some(insertedNode.copy(parent = Some(nextNode))))
      }
      result
  }

  override def max(): Key = root match {
    case Some(node) => node.value
    case None => throw new NullPointerException
  }

  /**
    * remove the greatest key from the tree
    * which should be root greaterNode's value
    *
    * @return
    */
  override def delMax(): Key = {
    if (root.isEmpty) {
      throw new NullPointerException
    }

    // final result or NPE thrown
    val maxValue = max()
    // decrease the size
    N -= 1

    N match {
      case 0 =>
        root = None
        last = None
      case 1 =>
        root = last
        last = None
      case _ => rearrangePqAfterDelete()
    }
    maxValue
  }

  private def rearrangePqAfterDelete(): Unit = {
    (root, last) match {
      case (Some(rootNode), Some(lastNode)) =>
        root = Some(rootNode copy (value = lastNode.value))
        // remove the last element from the tree (pqInit())
        // at this point heap order is violated
        // and order needs to be restored
        root = pqInit() map sink
      case (_, None) => root = None
    }
  }

  /**
    * Top-down re-heapify (sink) implementation
    *
    * @param sinkNode
    */
  override def sink(sinkNode: Node): Node = {
    val directionedNode = sinkNode.greaterChild()
    directionedNode map (nextNode => sinkHelper(sinkNode, nextNode._1, nextNode._2)) getOrElse sinkNode
  }

  private def sinkHelper(nextNode: Node, acc: Node, nextNodeDirection: Direction): Node = {
    // further improvement could be to enforce it through type system i.e. Tree, Branch, Leaf, Empty instead of Node
    def branchCase(child: Node, childNodeDirection: Direction) = {
      if (cmp.lt(nextNode.value, child.value)) {
        val GreaterSmallerNodes(greater, smaller) = swapNodeValues(child, nextNode)
        acc.addChild(
          greater.addChild(sink(smaller), childNodeDirection), nextNodeDirection
        )
      } else acc
    }

    nextNode match {
      // passed-in nextNode is a leaf => return accumulator
      case Node(_, None, None, _) => acc
      case Node(_, None, Some(rightChild), _) => branchCase(rightChild, RightDirection)
      case Node(_, Some(leftChild), None, _) => branchCase(leftChild, LeftDirection)
      case _ => nextNode.greaterChild().map(child => branchCase(child._1, child._2)).orNull
    }
  }

  /**
    * Method that swaps the values of the passed-in nodes
    *
    * @param greaterNode
    * @param smallerNode
    * @return both pair of the swap nodes
    */
  def swapNodeValues(greaterNode: Node, smallerNode: Node): GreaterSmallerNodes = {
    val greaterValue = greaterNode.value

    smallerNode match {
      // greaterNode is left child
      case Node(_, Some(x), _, _) if x == greaterNode =>
        val swappedNode = smallerNode.copy(
          value = greaterValue,
          left = Some(greaterNode copy (value = smallerNode.value))
        )
        GreaterSmallerNodes(swappedNode, swappedNode.left.orNull)
      // greaterNode is right child
      case Node(_, _, Some(x), _) if x == greaterNode =>
        val swappedNode = smallerNode.copy(
          value = greaterValue,
          right = Some(greaterNode copy (value = smallerNode.value))
        )
        GreaterSmallerNodes(swappedNode, swappedNode.right.orNull)
    }
  }

  /**
    * It is a necessity to continue swimming up even in the
    * event of no swap - in order to update the [[root]]
    * @param node
    */
  override def swim(node: Node): Node = {
    node.parent match {
      case Some(smallerParentNode) if cmp.gt(node.value, smallerParentNode.value) =>
        val reorderedNode: Node = swapNodeValues(node, smallerParentNode).greater
        swim(reorderedNode)
      case Some(largerParentNode) => swim(largerParentNode)
      case None => node
    }
  }

  private def pqInit(): Option[Node] = {
    import ca.vgorcinschi._

    def maybeParentOrSelf: Option[Node] => Option[Node] = maybeChildNode => {
      maybeChildNode flatMap (node => node.parent)
    }

    // set the last node to its parent
    last = maybeParentOrSelf(last)

    // iterate up the optional node hierarchy form last
    // until a None is reached meaning we've hit the root
    val parentNodeStreams: Stream[Option[Node]] = Stream.iterate(last)(maybeParentOrSelf).takeUntil(_ isDefined)

    // last parent node in the stream should be the new root
    // if the stream is empty return None
    // TODO: test that the pf really tries to get the last parent (should be the
    // TODO:  one that violates heap order)
    parentNodeStreams.applyOrElse(parentNodeStreams.size - 1, (_: Int) => None)
  }
}