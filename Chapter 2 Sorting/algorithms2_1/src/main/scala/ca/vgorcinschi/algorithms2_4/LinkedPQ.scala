package ca.vgorcinschi.algorithms2_4

import com.google.common.annotations.VisibleForTesting

import scala.language.postfixOps

/**
  * Ex 2.4.24 Priority queue with explicit links. Implement a priority queue using a heap-ordered binary
  * tree, but use a triply linked structure instead of an array. You will need three links per childBranch:
  * two to traverse down the tree and one to traverse up the tree. Your implementation should guarantee
  * logarithmic running time per operation, even if no maximum priority-queue size is known ahead time.
  */
class LinkedPQ[Key](implicit override protected val cmp: Ordering[_ >: Key])
  extends MaxPQ[Key] with PQNodeSupport[Key] {

  private var root: Tree[Key] = EmptyTree
  private var last: Tree[Key] = EmptyTree

  override def insert(value: Key): Unit = {
    N += 1
    root = insertHelper(root, value)
    root = swim(last)
  }

  @VisibleForTesting
  protected def insertHelper(nextTree: Tree[Key], value: Key): Branch[Key] = nextTree match {
    case EmptyTree =>
      last = Tree(value)
      last.asInstanceOf[Branch[Key]]
    case branch: Branch[Key] =>
      // compare left and right sizes to see where to go
      val leftSize = branch.left.size
      val rightSize = branch.right.size

      if (leftSize <= rightSize) {
        val newLeftBranch = insertHelper(branch.left, value)
        newLeftBranch.parent = Some(branch)
        branch.left = newLeftBranch
      } else {
        val newRightBranch = insertHelper(branch.right, value)
        newRightBranch.parent = Some(branch)
        branch.right = newRightBranch
      }
      branch
  }

  def removeLastBranch(currentTree: Tree[T]): (Tree[T], T) = {
    def shortenLeftBranch: (Tree[T], T) = {
      val (updatedLeftBranch, removedElem) = removeLastBranch(currentTree.left)
      if (updatedLeftBranch != EmptyTree) {
        (Tree(currentTree.elem,
          Tree(updatedLeftBranch.elem, updatedLeftBranch.left, updatedLeftBranch.right, Some(currentTree)),
          currentTree.right, currentTree.parent),
          removedElem)
      } else {
        (Tree(currentTree.elem, EmptyTree, currentTree.right, currentTree.parent),
          removedElem)
      }
    }

    def shortenRightBranch: (Tree[T], T) = {
      val (updatedRightBranch, removedElem) = removeLastBranch(currentTree.right)
      if (updatedRightBranch != EmptyTree) {
        (Tree(currentTree.elem, currentTree.left,
          Tree(updatedRightBranch.elem, updatedRightBranch.left, updatedRightBranch.right, Some(currentTree)),
          currentTree.parent),
          removedElem)
      } else {
        (Tree(currentTree.elem, currentTree.left, EmptyTree, currentTree.parent),
          removedElem)
      }
    }

    if (currentTree.left == EmptyTree && currentTree.right == EmptyTree) {
      return (EmptyTree, currentTree.elem)
    }

    if (currentTree.left.size > currentTree.right.size) shortenLeftBranch else shortenRightBranch
  }

  override def max(): Key = root match {
    case EmptyTree => throw new NullPointerException
    case _ => root.elem
  }

  /**
    * remove the greatest key from the tree
    * which should be root childBranch's value
    *
    * @return
    */
  override def delMax(): Key = {
    val maxValue = max()
    // decrease the size
    N -= 1

    if (N == 0) {
      root = EmptyTree
      last = EmptyTree
    } else {
      val (shortenedTree, removedValue) = removeLastBranch(root)
      root = Tree(removedValue, shortenedTree.left, shortenedTree.right)
      sink(root)
      last = selectNewLast(root)
    }
    maxValue
  }

  def selectNewLast(parent: Tree[T]): Tree[T] = {
    val longestBranch = Tree.longestBranch(parent.left, parent.right)

    if (longestBranch == EmptyTree) {
      return parent
    }

    selectNewLast(longestBranch)
  }

  override def sink(branch: Tree[T]): Unit = {
    if (branch.left == EmptyTree && branch.right == EmptyTree) return

    val greaterChild: Tree[T] = Tree.greaterChild(branch)

    if (greaterChild > branch) {
      swapNodeValues(greaterChild, branch)
      sink(greaterChild)
    }
  }

  def swapNodeValues(childBranch: Tree[Key], parentBranch: Tree[Key]): Unit = {
    val greaterValue = childBranch.elem
    childBranch.asInstanceOf[Branch[Key]].elem = parentBranch.elem
    parentBranch.asInstanceOf[Branch[Key]].elem = greaterValue
  }

  override def swim[U <: Tree[Key]](tree: U): Tree[Key] = {
    tree.parent match {
      case Some(smallerParentBranch) if cmp.lt(smallerParentBranch.elem, tree.elem) =>
        swapNodeValues(tree, smallerParentBranch)
        swim(smallerParentBranch)
      case Some(largerParentBranch) => swim(largerParentBranch)
      // reached root -> return
      case None => tree
    }
  }
}