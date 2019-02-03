package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort
import ca.vgorcinschi.algorithms2_3.MedianOfPartitioning._
import ca.vgorcinschi.algorithms2_3.QuickSort._

import scala.language.postfixOps

trait MedianOfPartitioning[T] {

  this: BaseSort[T] =>

  protected val MEDIAN_OF: Int


  def patchArray(a: Array[T], lo: Int, hi: Int): Int = {

    import ca.vgorcinschi.ArrayOps
    //return a new Median with adjusted min and max values
    def medianReductionFunction = (median: Median, elem: Int) => {
      var newMedian = median
      if (less(a, elem, median.indexOfMinValue)) newMedian = median copy (indexOfMinValue = elem)
      if (less(a, median.indexOfMaxValue, elem)) newMedian = median copy (indexOfMaxValue = elem)
      newMedian
    }

    //return the first element from the sample array which is not in the median
    def getMedianPivot(sampleArray: Array[Int], median: Median) = {
      sampleArray diff Array(median.indexOfMaxValue, median.indexOfMinValue) head
    }

    val sampleArray: Array[Int] = a.nIndicesSubarray(MEDIAN_OF, lo, hi)
    val median: Median = (Median(sampleArray.head, sampleArray.head) /: sampleArray) { case (acc, elem) => medianReductionFunction(acc, elem) }
    var pivot = getMedianPivot(sampleArray, median)

    exch(a, median.indexOfMinValue, lo)
    if (lo == pivot) pivot = median.indexOfMinValue
    exch(a, median.indexOfMaxValue, hi)
    if (hi == pivot) pivot = median.indexOfMaxValue
    //move pivot at position hi - 1
    exch(a, pivot, hi - 1)
    hi - 1
  }

  protected def partition(a: Array[T], lo: Int, hi: Int, pivotIndex: Int): Int = {
    var (i, j) = (lo, hi - 1) //left and right scan indices

    //removed bound checks
    def scan(index: Int, direction: Scan): Int = direction match {
      case Left => if (less(a(index), a(pivotIndex))) scan(index + 1, direction) else index
      case Right => if (index >= 0 && less(a(pivotIndex), a(index))) scan(index - 1, direction) else index
    }

    while (i < j) {
      //scan right, scan left, check for scan complete, and exchange
      i = scan(i + 1, Left)
      j = scan(j - 1, Right)
      if (i < j) exch(a, i, j)
    }

    val nextPivotIndex = if (less(a(i), a(j))) j else i
    exch(a, pivotIndex, nextPivotIndex) // put pivot into a(j)
    nextPivotIndex // with a(lo..j-1) <= a(j) <= a(j+1..hi)
  }
}

object MedianOfPartitioning {
  case class Median (indexOfMinValue: Int, indexOfMaxValue: Int)
}