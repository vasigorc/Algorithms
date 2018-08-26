package ca.vgorcinschi.algorithms2_2

import ca.vgorcinschi.algorithms2_1.Ex2_1_11

import scala.reflect.ClassTag

// Improvements from page 275
class Ex2_2_11[T<% Ordered[T]](implicit evidence: ClassTag[T]) extends MergeSort[T] {

  override def sort(a: Array[T], lo: Int, hi: Int): Array[T] = {
    if(hi <= lo) return a
    /*
      switching to insertion sort (shellsort) for small subarrays
      (length 15 or less, say) wil improve the running time of a typical
      mergesort implementation by 10-15%
     */
    if(a.length <= 15) {
      val shell = new Ex2_1_11[T]
      shell.sort(a)
    }
    else {
      val mid = lo + (hi - lo)/2
      sort(a, lo, mid)
      sort(a, mid+1, hi)
      /*
        We can reduce the running time to be linear for arrays that are already
        in order by adding a test to skip the call to merge if a(mid) is
        less or equal to a(mid+1)
       */
      if(a(mid) <= a(mid+1)) return a
      merge(a, lo, mid, hi)
    }
  }
}
