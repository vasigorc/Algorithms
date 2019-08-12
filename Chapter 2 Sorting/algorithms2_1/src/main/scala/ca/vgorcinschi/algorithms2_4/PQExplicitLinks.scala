package ca.vgorcinschi.algorithms2_4

import scala.reflect.ClassTag

/**
  * Ex 2.4.24 Priority queue with explicit links. Implement a priority queue using a heap-ordered binary
  * tree, but use a triply linked structure instead of an array. You will need three links per node:
  * two to traverse down the tree and one to traverse up the tree. Your implementation should guarantee
  * logarithmic running time per operation, even if no maximum priority-queue size is known ahead time.
  */
class PQExplicitLinks[Key: ClassTag : Ordering] {

  private case class Node(left: Option[Key], right: Option[Key], parent: Option[Key])

  private object Node {
    def empty(): Node = Node(None, None, None)
  }

}