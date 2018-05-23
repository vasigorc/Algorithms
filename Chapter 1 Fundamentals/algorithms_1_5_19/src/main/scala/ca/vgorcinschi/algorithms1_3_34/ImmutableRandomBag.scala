package ca.vgorcinschi.algorithms1_3_34

import ca.vgorcinschi.algorithms1_3_34.ImmutableRandomBag._

import scala.annotation.tailrec
import scala.util.Random

class ImmutableRandomBag[Item](maybeNode: Option[Node[Item]], override val size: Int) extends Iterable[Item]{

  override def isEmpty: Boolean = size == 0

  def add(item: Item) = {
    ImmutableRandomBag(Some(Node(item, maybeNode)), size +1)
  }

  override def iterator: Iterator[Item] = new RandomIterator[Item](maybeNode)

  private class RandomIterator[Item](first: Option[Node[Item]]) extends Iterator[Item]{

    var current: Int = 0
    val container: Vector[Item] = first match {
      case Some(node) => random(node)
      case None => Vector()
    }

    override def hasNext: Boolean = current < ImmutableRandomBag.this.size

    override def next(): Item = {
      val item = container(current)
      current += 1
      item
    }

    def random(first: Node[Item]) = {
      @tailrec
      def randomHelper(next: Option[Node[Item]], acc: List[Item]):List[Item]= next match {
        case None => acc
        case Some(node) => randomHelper(node.next, node.item::acc)
      }

      val items = randomHelper(Some(first), List[Item]())
      Random.shuffle(items).toVector
    }
  }
}

object ImmutableRandomBag{
  case class Node[Item](item: Item, next: Option[Node[Item]])
  def apply[Item](maybeNode: Option[Node[Item]], size: Int): ImmutableRandomBag[Item] = new ImmutableRandomBag(maybeNode, size)
}