package ca.vgorcinschi.algorithms2_4.immutable

import scala.annotation.tailrec
import scala.reflect.ClassTag

class ArrayMaxPQ[U : Ordering : ClassTag] private(private val priorityQueue: Array[U],
                                                  override val N: Int)
  extends AbstractMaxPQ[U] with FindMin[U]{

  import ArrayMaxPQ._

  override type Contravariant[K >: U] = ArrayMaxPQ[K]
  override type Nonvariant[_] = ArrayMaxPQ[U]

  private var maybeMin: Option[U] = None

  def this(initialCapacity: Int = ArrayMaxPQ.INITIAL_CAPACITY) {
    this(Array.fill(initialCapacity)(null.asInstanceOf[U]), 0)
  }

  def this(array: Array[U], nextPointer: Int, mMin: Option[U]) {
    this(array, nextPointer)
    maybeMin = mMin
  }

  override def min(): Option[U] = maybeMin

  /**
   *
   * @param v - value intended to be added
   * @tparam K - type parameter of the new PQ
   * @return new PQ containing all values of this + new value
   */
  override def insert[K >: U : Ordering : ClassTag](v: K): ArrayMaxPQ[K] = {
    val nextN = N + 1
    var nextPriorityQueue: Array[K] = copyArray(nextN)
    nextPriorityQueue(nextN) = v
    nextPriorityQueue = swim(nextPriorityQueue, nextN)
    val nMinValue = nextMinValue(v)
    ArrayMaxPQ(nextPriorityQueue, nextN, nMinValue)
  }

  private def nextMinValue[K >: U](v: K): Option[K] = {
    import Ordered._
    min().fold(Some(v))(current => {
      if (current < v.asInstanceOf[U]) Some(current)
      else Some(v)
    })
  }

  private def copyArray[K >: U : ClassTag](nextN: Int, extraF: Array[K] => Array[K]
                                           //do nothing by default
                                           = (array: Array[K]) => array): Array[K] = {
    val nextPriorityQueue = extraF(new Array[K](recalculateArraySize(nextN)))
    Array.copy(priorityQueue, 1, nextPriorityQueue, 1, N)
    nextPriorityQueue
  }

  private def recalculateArraySize(nextN: Int): Int = {
    val size = priorityQueue.length

    if (size == nextN) return nextN * 2
    else if (nextN > 0 && nextN == size / 4) return math.max(INITIAL_CAPACITY, size / 2)
    else size
  }

  override def max(): Option[U] = if (N < 1) None else Some(priorityQueue(1))

  /**
   * remove the max element if at least one
   * element is present and a new instance of
   * this PQ without the max element
   *
   * @return
   */
  override def delMax(): (Option[U], ArrayMaxPQ[U]) = {
    val maxValue = max()
    val nextN = N - 1
    var nextPriorityQueue: Array[U] = copyArray(nextN, array => {
      array(1) = priorityQueue(N)
      array
    })
    nextPriorityQueue = sink(nextPriorityQueue, nextN)
    (maxValue, ArrayMaxPQ(nextPriorityQueue, nextN, min()))
  }
}

object ArrayMaxPQ {
  import Ordered._

  private val INITIAL_CAPACITY: Int = 10
  // invisible outside class
  private def swim[U : Ordering : ClassTag](array: Array[U], lastIndex: Int):Array[U] = {
    var k = lastIndex
    while (k > 1 && less(array(k / 2), array(k))) {
      exch(array, k / 2, k)
      k = k / 2
    }
    array
  }

  /**
   * "Sink" element at index 1 by exchanging it with
   * one of the children until it finds a bigger child
   * @param array de-normalized priority queue
   * @param lastIndex - index of last counted value in the queue
   * @tparam U - ordered type
   * @return normalized priority queue
   */
  private def sink[U : Ordering : ClassTag](array: Array[U], lastIndex: Int): Array[U] = {

    @tailrec
    def innerLoop(currentIndex: Int): Unit = 2 * currentIndex match {
      case left if left <= lastIndex =>
        val right = left + 1
        val largestValueIndex = if (left < lastIndex && less(array, left, right)) {
          right
        } else left
        if (greater(array, currentIndex, largestValueIndex)) return
        exch(array, currentIndex, largestValueIndex)
        innerLoop(largestValueIndex)
      case _ =>
    }

    innerLoop(currentIndex = 1)
    array
  }

  private def exch[U](array: Array[U], x: Int, y: Int): Unit = {
    val temp = array(x)
    array(x) = array(y)
    array(y) = temp
  }

  private def greater[U : Ordering : ClassTag](array: Array[U], x: Int, y: Int): Boolean = {
    !less(array, x, y)
  }

  private def less[U : Ordering : ClassTag](array: Array[U], x: Int, y: Int): Boolean = {
    less(array(x), array(y))
  }

  private def less[U : Ordering](x: U, y: U): Boolean = x < y

  def apply[U : Ordering : ClassTag](priorityQueue: Array[U], N: Int, mMin: Option[U]): ArrayMaxPQ[U] = {
    new ArrayMaxPQ(priorityQueue, N, mMin)
  }

  def apply[U : Ordering : ClassTag](): ArrayMaxPQ[U] = new ArrayMaxPQ()
}
