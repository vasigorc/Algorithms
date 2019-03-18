package ca.vgorcinschi.algorithms2_3

import scala.reflect.ClassTag

/**
  * Exercise 2.3.20 Nonrecursive quicksort. Implement a nonrecursive version of quicksort based on a main
  * loop where a subarray is popped a stack to be partitioned, and the resulting subarrays are pushed onto
  * the stack. Note: Push the larger of the subarrays onto the stack first, which guarantees that the stack
  * will have at most lg N entries.
  */
class NonrecursiveQuicksort[T: ClassTag : Ordering] extends QuickSort[T] {

  /*
    List impl is recommended to be used for Stack purposes by Scala. Push is cons, pop is tail, and peek is head.
   */
  private var indicesStack = List[(Int, Int)]()

  override def sort(a: Array[T]): Array[T] = {
    if (a == null || a.length - 1 <= 1 || isSorted(a)) return a

    val N = a.length - 1
    indicesStack = List((1, N))

    while (indicesStack.nonEmpty) {
      val (lo, hi) = indicesStack.head
      indicesStack = indicesStack.tail
      val nextPivot = partition(a, lo, hi)

      if (lo < nextPivot - 1) {
        indicesStack = (lo, nextPivot - 1) :: indicesStack
      }

      if (nextPivot + 1 < hi) {
        indicesStack = (nextPivot + 1, hi) :: indicesStack
      }
    }
    a
  }
}