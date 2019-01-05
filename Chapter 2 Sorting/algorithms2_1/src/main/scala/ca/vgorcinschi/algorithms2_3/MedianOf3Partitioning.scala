package ca.vgorcinschi.algorithms2_3

import scala.reflect.ClassTag
import scala.util.Random
import ca.vgorcinschi.algorithms2_1.BaseSort._

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
class MedianOf3Partitioning[T: ClassTag : Ordering] extends QuickSort[T] {

  override protected def sort(a: Array[T], lo: Int, hi: Int): Array[T] = {
    def getSample(a: Array[T]): Array[T] = {
      val sampleArrayOffset = Random.nextInt(a.length - 3)
      val sampleArray: Array[T] = a.slice(sampleArrayOffset, sampleArrayOffset + 3)
      val minMax: (T, T) = ((sampleArray.head, sampleArray.head) /: sampleArray) { case ((minV, maxV), current) =>
        (min(minV, current), max(maxV, current))
      }
      minMax
      ???
    }

    if (a.length <= 3)
      super.sort(a, lo, hi)
    val sampleOf3Array = getSample(a)

    ???
  }

}