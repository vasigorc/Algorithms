package ca.vgorcinschi.algorithms2_3

import scala.reflect.ClassTag

/**
  * Exercise 2.3.18 Median-of-3 partitioning. Add median-of-3 partitioning to quicksort, as described in the text
  * (see page 296). Run doubling tests to determine the effectiveness of the change.
  * From page 296: ...is to use the median of a small sample of items taken from the subarray as the partitioning item.
  * ...As a bonus, we can use the sample items as sentinels at the ends of the array and remove both 
  * array bounds tests in partition.
  *
  * @param ev$1
  * @param ev$2
  * @tparam T must be Comparable and needs runtime type information for Array
  */
class MedianOf3Partitioning[T: ClassTag : Ordering] extends QuickSort[T] with MedianOfPartitioning[T] {

  protected val MEDIAN_OF = 3

  override protected def sort(a: Array[T], lo: Int, hi: Int): Array[T] = {
    def medianOf3 = {
      val center = (lo + hi) / 2
      if (less(a(center), a(lo))) exch(a, lo, center)
      if (less(a(hi), a(lo))) exch(a, lo, hi)
      if (less(a(hi), a(center))) exch(a, center, hi)
      //move pivot at position hi - 1
      exch(a, center, hi - 1)
      hi - 1
    }

    if ((hi - lo) > MEDIAN_OF) {
      val j = partition(a, lo, hi, medianOf3)
      sort(a, lo, j - 1)
      sort(a, j + 1, hi)
    } else super.sort(a, lo, hi)
  }
}