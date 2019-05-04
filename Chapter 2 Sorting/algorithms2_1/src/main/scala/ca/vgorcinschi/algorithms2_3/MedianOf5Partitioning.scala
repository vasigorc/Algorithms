package ca.vgorcinschi.algorithms2_3

import scala.reflect.ClassTag

class MedianOf5Partitioning[T: ClassTag : Ordering] extends QuickSort[T] with MedianOfPartitioning[T] {

  override protected val MEDIAN_OF = 5

  override protected def sort(a: Array[T], lo: Int, hi: Int): Array[T] = {
    if ((hi - lo) > MEDIAN_OF) {
      patchArray(a, lo, hi)
      val j = partition(a, lo, hi)
      sort(a, lo, j - 1)
      sort(a, j + 1, hi)
    } else super.sort(a, lo, hi)
  }
}