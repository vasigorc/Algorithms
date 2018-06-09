package ca.vgorcinschi.algorithms_2

import scala.reflect.ClassTag

/**
  * Sublinear extra space. Develop a merge implementation that reduces the extra
  * space requirements to max(M, N/M), based on the following idea, Divide the array
  * into N/M blocks of size M (for simplicity, assume that N is a multiple of M). Then,
  * (i) considering the blocks as items with their first key as the sort key, sort them using
  * selection sort; and (ii) run through the array merging the first block with the second, then
  * the second with the third, and so fourth.
  *
  * to me it sounds like bottom-up mergesort implementation with the exception that all sorts
  * on subarrays are done using shellsort
  *
  * @tparam T - bound type for any Ordered (Comparable) type
  */
class Ex2_2_12[T<% Ordered[T]](implicit evidence: ClassTag[T]) extends MergeSort[T]{

  override def sort(a: Array[T]): Array[T] = {
    val N = a.length
    aux = new Array[T](a.length)
    a
  }
}
