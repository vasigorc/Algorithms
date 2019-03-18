package ca.vgorcinschi.algorithms2_3

import scala.reflect.ClassTag
import ca.vgorcinschi._

/**
  * Exercise 2.3.20 Nonrecursive quicksort. Implement a nonrecursive version of quicksort based on a main
  * loop where a subarray is popped a stack to be partitioned, and the resulting subarrays are pushed onto
  * the stack. Note: Push the larger of the subarrays onto the stack first, which guarantees that the stack
  * will have at most lg N entries.
  */
object NonrecursiveQuicksort {

  def sort[T: ClassTag : Ordering](a: Array[T]): Array[T] = {
    if (a == null || a.length - 1 <= 1 || isSorted(a)) return a
    val N = a.length - 1

    val pivot = a(0)
    val lo = a(1)
    val hi = a(N)
    ???
  }
}