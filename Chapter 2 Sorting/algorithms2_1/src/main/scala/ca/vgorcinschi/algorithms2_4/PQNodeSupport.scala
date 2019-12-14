package ca.vgorcinschi.algorithms2_4

import scala.language.higherKinds

trait PQNodeSupport[Key] {

  // classes that implement PQNodeSupport must also extend MaxPQ
  self: MaxPQ[Key] =>

  implicit protected val cmp: Ordering[_ >: Key]

  type T = Key

  import Tree._

  /**
    * [[Tree]] implementation, especially it's
    * equality part, is mostly based on the example from Ch. 30
    * of Odersky, Spoon, Venner's "Programming in Scala"
    *
    * @tparam T
    */
  sealed trait Tree[+T] {
    def elem: T

    def left: Tree[T]

    def right: Tree[T]

    def parent: Option[Tree[T]]

    def size: Int

    def >[K >: T](that: Tree[K])(implicit ev: K <:< Key): Boolean = {
      if (this == that) false
      else if (that == EmptyTree || this == EmptyTree) false
      else if (cmp.gt(this.elem, that.elem)) true
      else false
    }
  }

  object EmptyTree extends Tree[Nothing] {
    override def elem: Nothing = fail("EmptyTree.elem")

    override def left: Tree[Nothing] = fail("EmptyTree.left")

    override def right: Tree[Nothing] = fail("EmptyTree.right")

    override def parent: Option[Tree[Nothing]] = fail("EmptyTree.parent")

    override def size: Int = 0
  }

  final class Branch[T](
                         var elem: T,
                         var left: Tree[T],
                         var right: Tree[T],
                         var parent: Option[Tree[T]]
                       ) extends Tree[T] {

    override def equals(other: Any): Boolean = other match {
      case that: Branch[_] => (that canEqual this) &&
        this.elem == that.elem &&
        this.left == that.left &&
        this.right == that.right &&
        this.parent == that.parent &&
        this.size == that.size
      case _ => false
    }

    def canEqual(other: Any): Boolean = other.isInstanceOf[Branch[_]]

    override def hashCode(): Int = (elem, left, right, parent, size).##

    override def size: Int = left.size + right.size + 1
  }

  object Tree {
    def fail(message: String) = throw new NoSuchElementException(message)

    def apply(elem: T,
              left: Tree[T] = EmptyTree,
              right: Tree[T] = EmptyTree,
              parent: Option[Tree[T]] = None): Branch[T] = new Branch[T](elem, left, right, parent)

    def greaterChild(tree: Tree[T]): Tree[T] = if (tree.right > tree.left) tree.right else tree.left

    def longestBranch(left: Tree[T], right: Tree[T]): Tree[T] = (left, right) match {
      case (EmptyTree, EmptyTree) => EmptyTree
      case (_, EmptyTree) => left
      case (EmptyTree, _) => right
      case _ => if (left.size > right.size) left else right
    }
  }

  def swim[U <: Tree[Key]](tree: U): Tree[Key]

  def sink(branch: Tree[T]): Unit
}
