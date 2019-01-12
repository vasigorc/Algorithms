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
  * @tparam T
  */
class MedianOf3Partitioning[T: ClassTag : Ordering] extends QuickSort[T] with MedianOfPartitioning[T] {

  protected val MEDIAN_OF = 3

  override protected def sort(a: Array[T], lo: Int, hi: Int): Array[T] = {
    if (hi > lo || (hi - lo) > MEDIAN_OF) {
      val sampleArray = getSample(a.slice(lo, hi), MEDIAN_OF)
      patchArray(a, lo, hi, sampleArray)
      val j = partition(a, lo, hi)
      sort(a, lo, j - 1)
      sort(a, j + 1, hi)
    } else super.sort(a, lo, hi)

  }
}