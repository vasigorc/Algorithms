package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.algorithms2_3.{Direction, LeftDirection, RightDirection}

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

    def greaterChild() : Option[(Node, Direction)] = {
      (left, right) match {
        case (None, None) => None
        case (_, None) => left.flatMap(n => Some((n, LeftDirection)))
        case (None, _) => right.flatMap(n => Some((n, RightDirection)))
        case (Some(l), Some(r)) => if (cmp.gt(l.value,r.value)) left.flatMap(n => Some((n, LeftDirection)))
                                   else right.flatMap(n => Some((n, RightDirection)))
      }
    }

    def addChild(childNode: Node, direction: Direction): Node = direction match {
      case LeftDirection => this.copy(left = Some(childNode))
      case RightDirection => this.copy(right = Some(childNode))
    }
  }

  def swim(s: Node): Node

  def sink(s: Node): Node
}
