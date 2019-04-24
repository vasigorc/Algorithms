package ca

import scala.annotation.tailrec
import scala.collection.immutable.Queue
import scala.reflect.ClassTag
import scala.util.Random

package object vgorcinschi {

  val easyShellSort: Array[Char] = "EASYSHELLSORTQUESTION".toCharArray

  implicit class ArrayOps[T](a: Array[T]) {

    private val N = a.length - 1

    def subArrayOfSize(subArraySize: Int, from: Int = 0, to: Int = N): Array[Int] = {
      validateIndicesSubArray(subArraySize, from, to)
      //nextInt is exclusive, so need to add + 1
      val offset = from + Random.nextInt(to + 1 - (from + subArraySize))
      offset until offset + subArraySize toArray
    }

    private def validateIndicesSubArray(subArraySize: Int, from: Int, to: Int): Unit = {
      //Note that the TARGETED_SEGMENT can potentially  be the entire length of array a
      val TARGETED_SEGMENT: Int = to - from

      require(to <= N + 1, () => s"""The required "to" bound ($to) is outside target array's available upper bound: $N""")
      require(Seq(subArraySize, from, to) forall (_ >= 0), () => s"this method doesn't accept negative integers as " +
        s"arguments")
      require(to >= from, () => s""""from"($from) can not be bigger than "to"($to)""")
      require(TARGETED_SEGMENT >= subArraySize, () => s"Cannot generate a sub-array of requested size $subArraySize." +
        s"Targeted segment $TARGETED_SEGMENT is less.")
    }
  }

  implicit class StreamOps[T](stream: Stream[T]) {
    def takeUntil(predicate: T => Boolean): Stream[T] = {
      stream.span(predicate) match {
        case (matching, missmatching) => matching #::: missmatching.take(1)
      }
    }
  }

  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  // From Stepanov's and Rose's "From Mathematics to Generic Programming"
  def gcdOldWay(a: Int, b: Int): Int = {
    var x = a
    var y = b
    while (y != 0) {
      x = x % y
      //swap variables' values
      x = x + y
      y = x - y
      x = x - y
    }
    x
  }

  /*
     based on solution from rosettacode.org but changed for Streams. We want to have Stream(1,?) collection
     + adding reverse because we will need take(1) in most cases
   */
  def descedingValidDivisors(n: Int): Stream[Int] = Stream.range(1, n / 2).filter(i => n % i == 0).reverse

  def gd(n: Int): Int = descedingValidDivisors(n).take(1).head

  //test if array is sorted
  def isSorted[T <% Ordered[T]](a: Array[T]): Boolean = {
    (1 until a.length).forall(i => a(i - 1) <= a(i))
  }

  def isSorted[T <% Ordered[T]](s: Seq[T]): Boolean = s match {
    case _ if s.size < 2 => true
    case x :: xs => {
      if (x < xs.head) isSorted(xs) else false
    }
  }

  /*
    Exercise 2.2.14 Merging sorted queues. Develop a function that takes two queues of sorted itemsas arguments and returns
    a queue that resuts from merging the queues into sorted order.
   */
  implicit class QueueOps[T: ClassTag : Ordering](queue: Queue[T]) {
    def mergeQueue(that: Queue[T]): Queue[T] = {
      if (that == null) return queue
      import Ordered._

      @tailrec
      def internal(first: Queue[T], second: Queue[T], acc: Queue[T]): Queue[T] = (first, second) match {
        case (Queue(), Queue()) => acc
        case (q, Queue()) => acc ++: q
        case (Queue(), y) => acc ++: y
        case (q, y) => if (q.head < y.head) internal(q.tail, y, acc.enqueue(q.head))
        else internal(q, y.tail, acc.enqueue(y.head))
      }

      internal(queue, that, Queue())
    }
  }

}
