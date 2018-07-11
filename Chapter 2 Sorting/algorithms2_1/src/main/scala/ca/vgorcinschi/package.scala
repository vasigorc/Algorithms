package ca

import scala.annotation.tailrec
import scala.collection.immutable.Queue
import scala.reflect.ClassTag

package object vgorcinschi {

  val easyShellSort: Array[Char] = "EASYSHELLSORTQUESTION".toCharArray

  implicit class StreamOps[T](stream: Stream[T]) {
    def takeUntil(predicate: T => Boolean):Stream[T] = {
      stream.span(predicate) match {
        case (matching, missmatching) => matching #::: missmatching.take(1)
      }
    }
  }

  def gcd(a: Int, b: Int): Int = if(b==0) a else gcd(b, a % b)

  // From Stepanov's and Rose's "From Mathematics to Generic Programming"
  def gcdOldWay(a: Int, b: Int):Int = {
    var x =a
    var y =b
    while(y != 0){
      x = x % y
      //swap variables' values
      x = x+y
      y = x-y
      x = x-y
    }
    x
  }

  /*
     based on solution from rosettacode.org but changed for Streams. We want to have Stream(1,?) collection
     + adding reverse because we will need take(1) in most cases
   */
  def descedingValidDivisors(n: Int) = Stream.range(1, n/2).filter(i => n % i == 0).reverse

  def gd(n: Int):Int = descedingValidDivisors(n).take(1).head

  //test if array is sorted
  def isSorted[T <% Ordered[T]](a: Array[T]):Boolean ={
    (1 until a.length).forall(i => a(i-1) <= a(i))
  }

  /*
    Exercise 2.2.14 Merging sorted queues. Develop a function that takes two queues of sorted itemsas arguments and returns
    a queue that resuts from merging the queues into sorted order.
   */
  implicit class QueueOps[T : ClassTag : Ordering](queue: Queue[T]){
    def mergeQueue(that: Queue[T]):Queue[T] ={
      require(queue != null && that != null, "Neither of the queues was initialized")
      import Ordered._

      @tailrec
      def internal(first: Queue[T], second: Queue[T], acc: Queue[T]): Queue[T] =(first, second) match {
        case (Queue(), Queue()) => acc
        case (q, Queue()) => acc ++: q
        case (Queue(), y) => acc ++: y
        case (q, y) => if(q.head < y.head) internal(q.tail, y, acc.enqueue(q.head))
        else internal(q, y.tail, acc.enqueue(y.head))
      }

      internal(queue, that, Queue())
    }
  }
}
