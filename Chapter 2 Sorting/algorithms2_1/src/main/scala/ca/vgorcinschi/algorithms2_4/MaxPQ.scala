package ca.vgorcinschi.algorithms2_4

import scala.reflect.ClassTag

/**
  *
  * @param capacity - defaults to 10
  * @tparam Key - must be comparable
  */
class MaxPQ[Key: ClassTag : Ordering](val capacity: Int = 10) {

  import Ordered._

  private implicit def optToKey(maybeKey: Option[Key]): Key = maybeKey.get

  private var pq: Array[Option[Key]] = new Array(capacity)
  //nr. of keys present in the queue
  private var N: Int = 0

  //insert a key into a Priority Queue
  def insert(v: Key): Unit = {
    N += 1
    pq(N) = Some(v)
    swim(N)
  }

  //return and remove the largest key
  def delMax(): Key = {
    val maxValue = max()
    N -= 1
    exch(1, N)
    pq(N) = None //avoid loitering
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
    def innerLoop(k: Int): Unit = 2 * k match {
      case x if x <= N =>
        val j = if (x < N && less(x, x + 1)) {
          x + 1
        } else x
        if (!less(k, j)) return
        innerLoop(j)
      case _ =>
    }

    innerLoop(key)
  }

  private def less(i: Int, j: Int): Boolean = pq(i) < pq(j)

  private def exch(i: Int, j: Int): Unit = {
    val temp = pq(i)
    pq(i) = pq(j)
    pq(j) = temp
  }
}