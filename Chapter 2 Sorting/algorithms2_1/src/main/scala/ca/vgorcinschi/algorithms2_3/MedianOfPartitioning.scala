package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort
import ca.vgorcinschi.algorithms2_3.MedianOfPartitioning._
import ca.vgorcinschi.algorithms2_3.QuickSort._

import scala.annotation.tailrec
import scala.language.postfixOps

trait MedianOfPartitioning[T] {

  this: BaseSort[T] =>

  protected val MEDIAN_OF: Int

  def patchArrayAndGetPivot(a: Array[T], lo: Int, hi: Int): Int = {

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
    var pivotIndex: Int = getMedian(sampleArray, minMax)

    exch(a, minMax.indexOfMinValue, lo)
    if (lo == pivotIndex) {
      pivotIndex = minMax.indexOfMinValue
    }
    /*
      if indexOfMaxValue was at lo, new item at indexOfMinValue
      has actually become the largest value from the sample array
      (despite what it's name implies)
     */
    if (lo == minMax.indexOfMaxValue) {
      exch(a, minMax.indexOfMinValue, hi)
    } else {
      exch(a, minMax.indexOfMaxValue, hi)
    }
    if (hi == pivotIndex) {
      pivotIndex = minMax.indexOfMaxValue
    }
    //move pivotIndex at position hi - 1
    exch(a, pivotIndex, hi - 1)
    hi - 1
  }

  protected def partition(a: Array[T], lo: Int, hi: Int, pivotIndex: Int): Int = {
    var (i, j) = (lo, hi - 1) //left and right scan indices

    //removed bound checks
    @tailrec
    def scan(index: Int, direction: Scan): Int = direction match {
      case Left => if (less(a(index), a(pivotIndex))) scan(index + 1, direction) else index
      case Right => if (less(a(pivotIndex), a(index))) scan(index - 1, direction) else index
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
  case class MinMax(indexOfMinValue: Int, indexOfMaxValue: Int)

  //return the first element from the sample array which is not in the minMax
  def getMedian(sampleArray: Array[Int], median: MinMax): Int = {
    sampleArray diff Array(median.indexOfMaxValue, median.indexOfMinValue) head
  }
}