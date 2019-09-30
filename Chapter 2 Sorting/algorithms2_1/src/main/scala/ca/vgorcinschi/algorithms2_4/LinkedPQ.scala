package ca.vgorcinschi.algorithms2_4

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

  override def insert(value: Key): Unit = {
    N += 1
    root = Some(insert(root, value))
    root = Some(swim(last.get))
  }

  /**
    * The purpose of this helper method is to recursively look
    * for a leaf in the shortest path while maintaining the existing
    * tree structure
    *
    * @param maybeNextNode - this should be existing greaterNode
    * @param value
    * @return current tree with new greaterNode appended as per described above
    */
  private def insert(maybeNextNode: Option[Node], value: Key): Node = maybeNextNode match {
    case None =>
      last = Some(Node(value = value))
      last.orNull
    case Some(nextNode) =>
      // compare left and right sizes to see where to go
      val leftSize = nextNode.left.map(_.size()).getOrElse(0)
      val rightSize = nextNode.right.map(_.size()).getOrElse(0)

      val result = if (leftSize <= rightSize) {
        val insertedNode = insert(nextNode.left, value)
        nextNode.copy(left = Some(insertedNode.copy(parent = Some(nextNode))))
      } else {
        val insertedNode = insert(nextNode.right, value)
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
    // guard pattern
    if (root.isEmpty) {
      throw new NullPointerException
    }

    // final result or NPE thrown
    val maxValue = max()
    // decrease the size
    N -= 1

    def rearrangePq(): Unit = {
      (root, last) match {
        case (Some(rootNode), Some(lastNode)) =>
          root = Some(rootNode copy (value = lastNode.value))
          // remove the last element from the tree
          root = pqInit()
          // at this point heap order is violated
          // and order needs to be restored
          root = Some(sink(rootNode))
        case (_, None) => root = None
      }
    }

    N match {
      case 0 =>
        root = None
        last = None
      case 1 =>
        root = last
        last = None
      case _ => rearrangePq()
    }
    maxValue
  }


  /**
    * Top-down reheapify (sink) implementation
    *
    * @param topNode - violates heap order
    */
  override def sink(topNode: Node): Node = {

    ???
  }

  /**
    * Method that swaps the values of the passed-in nodes
    *
    * @param greaterNode - bigger greaterNode in child position
    * @param smallerNode
    * @return ex-smallerNode with swapped to greater value
    */
  def exchNodes(greaterNode: Node, smallerNode: Node): Node = {
    val greaterValue = greaterNode.value
    smallerNode match {
      // greaterNode is left child
      case Node(_, Some(x), _, _) if x == greaterNode =>
        smallerNode copy(
          value = greaterValue,
          left = Some(greaterNode copy (value = smallerNode.value))
        )
      // greaterNode is right child
      case Node(_, _, Some(x), _) if x == greaterNode =>
        smallerNode copy(
          value = greaterValue,
          right = Some(greaterNode copy (value = smallerNode.value))
        )
    }
  }

  /**
    * It is a necessity to continue swimming up even in the
    * event of no swap - in order to update the [[root]]
    * updated
    *
    * @param node
    */
  override def swim(node: Node): Node = {
    node.parent match {
      case Some(smallerParentNode) if cmp.gt(node.value, smallerParentNode.value) =>
        val reorderedNode: Node = exchNodes(node, smallerParentNode)
        swim(reorderedNode)
      case Some(largerParentNode) => swim(largerParentNode)
      case None => node
    }
  }

  /**
    * while loop stream equivalent
    *
    * @return copy of this PQ without last element
    */
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
    parentNodeStreams.applyOrElse(parentNodeStreams.size - 1, (_: Int) => None)
  }
}