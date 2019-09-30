package ca.vgorcinschi.algorithms2_4

trait PQNodeSupport[Key] {

  implicit protected val cmp: Ordering[_ >: Key]

  case class Node(value: Key, left: Option[Node] = None,
             right: Option[Node] = None,
             parent: Option[Node] = None) {

    def size(): Int = {
      val childrenSize: Int = (left, right) match {
        case (Some(_), Some(_)) => left.size + right.size
        case (Some(_), None) => left.size
        case (None, Some(_)) => right.size
        case _ => 0
      }
      1 + childrenSize
    }
  }

  def swim(s: Node): Node

  def sink(s: Node): Node
}
