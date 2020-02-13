package ca.vgorcinschi.algorithms2_4

import scala.reflect.ClassTag
import util.control.Breaks._

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
    val movingPart = priorityQueue(key)
    var k = key

    breakable {
      while (2 * k <= N) {
        val left = 2 * k
        val right = 2 * k + 1
        val biggestChildIndex = if (left < N && less(left, right))  right  else left
        if (!less(k, biggestChildIndex)) break
        priorityQueue(biggestChildIndex) = priorityQueue(k)
        k = biggestChildIndex
      }
    }
    priorityQueue(k) = movingPart
  }

  override protected def swim(key: Int): Unit = {
    val movingPart = priorityQueue(key)
    var k = key

    while (k > 1 && less(k / 2, k)) {
      priorityQueue(k / 2) = priorityQueue(k)
      k /= 2
    }
    priorityQueue(k) = movingPart
  }
}