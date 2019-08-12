package ca.vgorcinschi.algorithms2_4

import scala.reflect.ClassTag

/**
  * Ex 2.4.24 Priority queue with explicit links. Implement a priority queue using a heap-ordered binary
  * tree, but use a triply linked structure instead of an array. You will need three links per node:
  * two to traverse down the tree and one to traverse up the tree. Your implementation should guarantee
  * logarithmic running time per operation, even if no maximum priority-queue size is known ahead time.
  */
class PQExplicitLinks[Key](implicit tag: ClassTag[Key],
                           override protected val cmp: Ordering[_ >: Key])
  extends MaxPQ[Key] {

  private case class Node(left: Option[Key] = None,
                          right: Option[Key] = None,
                          parent: Option[Key] = None)

  override def insert(v: Key): Unit = ???

  override def max(): Key = ???

  override def delMax(): Key = ???

  override def isEmpty(): Boolean = ???

  override def size(): Int = ???
}