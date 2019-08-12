package ca.vgorcinschi.algorithms2_4

import scala.reflect.ClassTag

/**
  *
  * @param capacity - defaults to 10
  * @tparam Key - must be comparable
  */
class ArrayMaxPQ[Key](val capacity: Int = 10)(implicit tag: ClassTag[Key],
                                              override protected val cmp: Ordering[_ >: Key])
  extends MaxPQ[Key] {

  private implicit def optToKey(maybeKey: Option[Key]): Key = maybeKey.get

  protected var pq: Array[Option[Key]] = Array.fill(capacity)(None)
  //nr. of keys present in the queue
  protected var N: Int = 0

  //insert a key into a Priority Queue
  def insert(v: Key): Unit = {
    N += 1
    pq(N) = Some(v)
    swim(N)
  }

  //return and remove the largest key
  def delMax(): Key = {
    val maxValue = max()
    exch(1, N)
    N -= 1
    pq(N + 1) = None //avoid loitering
    sink(1)
    maxValue
  }

  //is the Priority Queue empty?
  def isEmpty: Boolean = N == 0

  //return the largest key
  def max(): Key = pq(1)

  //number of entries in the Priority Queue
  def size(): Int = N

  private def swim(key: Int): Unit = {
    def innerLoop(k: Int): Unit = k match {
      case _ if k > 1 && less(k / 2, k) =>
        exch(k / 2, k)
        innerLoop(k / 2)
      case _ => //exit
    }

    innerLoop(key)
  }

  private def sink(key: Int): Unit = {
    def innerLoop(candidateIndex: Int): Unit = 2 * candidateIndex match {
      case left if left <= N =>
        val smallestChildIndex = if (left < N && less(left, left + 1)) {
          left + 1
        } else left
        if (!less(candidateIndex, smallestChildIndex)) return
        exch(candidateIndex, smallestChildIndex)
        innerLoop(smallestChildIndex)
      case _ =>
    }

    innerLoop(key)
  }

  private def less(i: Int, j: Int): Boolean = cmp.lt(pq(i), pq(j))

  private def exch(i: Int, j: Int): Unit = {
    val temp = pq(i)
    pq(i) = pq(j)
    pq(j) = temp
  }
}