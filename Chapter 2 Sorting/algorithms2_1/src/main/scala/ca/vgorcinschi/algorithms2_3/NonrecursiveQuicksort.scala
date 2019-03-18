package ca.vgorcinschi.algorithms2_3

import scala.reflect.ClassTag

/**
  * Exercise 2.3.20 Nonrecursive quicksort. Implement a nonrecursive version of quicksort based on a main
  * loop where a subarray is popped a stack to be partitioned, and the resulting subarrays are pushed onto
  * the stack. Note: Push the larger of the subarrays onto the stack first, which guarantees that the stack
  * will have at most lg N entries.
  */
object NonrecursiveQuicksort {

  def sort[T: ClassTag : Ordering](a: Array[T]): Array[T] ={
    ???
  }
}