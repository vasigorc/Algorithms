package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort
import ca.vgorcinschi.algorithms2_3.MedianOfPartitioning._

import scala.language.postfixOps

trait MedianOfPartitioning[T] {

  this: BaseSort[T] =>

  protected val MEDIAN_OF: Int

  def patchArray(a: Array[T], lo: Int, hi: Int): Unit = {

    import ca.vgorcinschi.ArrayOps
    //return a new MinMax with adjusted min and max values
    def medianReductionFunction = (median: MinMax, index: Int) => {
      var newMedian = median
      if (less(a, index, median.indexOfMinValue)) newMedian = median copy (indexOfMinValue = index)
      if (less(a, median.indexOfMaxValue, index)) newMedian = median copy (indexOfMaxValue = index)
      newMedian
    }

    val sampleArray: Array[Int] = a.subArrayOfSize(MEDIAN_OF, lo, hi)
    val minMax: MinMax = (MinMax(sampleArray.head, sampleArray.head) /: sampleArray.tail) { case (acc, elem) => medianReductionFunction(acc, elem) }
    val pivotIndex: Int = getMedian(sampleArray, minMax)

    exch(a, pivotIndex, lo)
    exch(a, minMax.indexOfMinValue, lo + 1)
    exch(a, minMax.indexOfMaxValue, hi)
  }

}

object MedianOfPartitioning {
  case class MinMax(indexOfMinValue: Int, indexOfMaxValue: Int)

  //return the first element from the sample array which is not in the minMax
  def getMedian(sampleArray: Array[Int], median: MinMax): Int = {
    sampleArray diff Array(median.indexOfMaxValue, median.indexOfMinValue) head
  }
}