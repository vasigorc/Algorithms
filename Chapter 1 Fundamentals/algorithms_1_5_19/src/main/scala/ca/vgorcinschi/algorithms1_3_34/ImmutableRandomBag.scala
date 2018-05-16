package ca.vgorcinschi.algorithms1_3_34

import scala.collection.IterableLike

case class Node[Item](item: Item, next: Option[Node[Item]])

class ImmutableRandomBag[Item](maybeNode: Option[Node[Item]], size: Int) extends Iterable[Item]
  with IterableLike[Item, ImmutableRandomBag[Item]]{

  override def isEmpty: Boolean = size == 0

  def add(item: Item) = {
    ImmutableRandomBag(Some(Node(item, maybeNode)), size +1)
  }

  override def iterator: Iterator[Item] = ???
}

object ImmutableRandomBag{

  def apply[Item](maybeNode: Option[Node[Item]], size: Int): ImmutableRandomBag[Item] = new ImmutableRandomBag(maybeNode, size)
}