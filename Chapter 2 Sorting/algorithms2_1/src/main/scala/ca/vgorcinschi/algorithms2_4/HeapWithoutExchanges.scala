package ca.vgorcinschi.algorithms2_4

import scala.annotation.tailrec
import scala.reflect.ClassTag

/**
 * Ex 2.4.26 Heap without exchanges. Because the exch() primitive is used in the sink()
 * and swim() operations, the items are loaded and stored twice as often as necessary.
 * Give more efficient implementations that avoid this inefficiency, à la insertion
 * sort [[ca.vgorcinschi.algorithms2_1.Ex2_1_25]]
 *
 * @param tag
 * @param cmp
 * @tparam Key
 */
class HeapWithoutExchanges[Key](implicit tag: ClassTag[Key],
                                override protected val cmp: Ordering[_ >: Key])
  extends ArrayMaxPQ[Key] {

  override protected def sink(key: Int): Unit = {
    val movingGenre = priorityQueue(key)

    @tailrec
    def innerLoop(currentIndex: Int, previousIndex: Int): Int = currentIndex match {
      case left if left <= N =>
        val largestValueIndex = if (left < N && less(left, left + 1)) left + 1 else left
        if (greaterValue(movingGenre, priorityQueue(largestValueIndex))) return previousIndex
        priorityQueue(previousIndex) = priorityQueue(largestValueIndex)
        innerLoop(2 * largestValueIndex, largestValueIndex)
      case _ => previousIndex
    }

    priorityQueue(innerLoop(2 * key, key)) = movingGenre
  }

  // reducing # of array references swap to max 1 per insert
  override protected def swim(key: Int): Unit = {
    val movingGenre = priorityQueue(key)

    @tailrec
    def innerLoop(currentIndex: Int, previousIndex: Int): Int = currentIndex match {
      case c if c > 0 && less(priorityQueue(c), movingGenre) =>
        priorityQueue(previousIndex) = priorityQueue(c)
        innerLoop(c / 2, c)
      case _ => previousIndex
    }
    priorityQueue(innerLoop(key /2, key)) = movingGenre
  }

  def less(maybeA: Option[Key], maybeB: Option[Key]): Boolean = (maybeA, maybeB) match {
    case (Some(a), Some(b)) => cmp.lt(a, b)
    case (None, Some(_)) => true
    case _ => false
  }

  private def greaterValue= (maybeA: Option[Key], maybeB: Option[Key]) => !less(maybeA, maybeB)
}