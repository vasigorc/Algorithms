package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort
import ca.vgorcinschi.algorithms2_3.MedianOfPartitioning._
import ca.vgorcinschi.algorithms2_3.QuickSort._

import scala.language.postfixOps
import scala.util.Random

trait MedianOfPartitioning[T] {

  this: BaseSort[T] =>

  protected val MEDIAN_OF: Int

  protected def sampleArrayIndices (a: Array[T]): Array[Int] = {
    val sampleArrayOffset = Random.nextInt(a.length - MEDIAN_OF)
    sampleArrayOffset until sampleArrayOffset + MEDIAN_OF toArray
  }

  def patchArray(a: Array[T], lo: Int, hi: Int): Int = {

    //return a new Median with adjusted min and max values
    def medianReductionFunction = (median: Median, elem: Int) => {
      var newMedian = median
      if (less(a, elem, median.minz)) newMedian = median copy (minz = elem)
      if (less(a, median.maxz, elem)) newMedian = median copy (maxz = elem)
      newMedian
    }

    //return the first element from the sample array which is not in the median
    def getMedianPivot(sampleArray: Array[Int], median: Median) = {
      sampleArray diff Array(median.maxz, median.minz) head
    }

    val sampleArray: Array[Int] = sampleArrayIndices(a)
    val median: Median = (Median(sampleArray.head, sampleArray.head) /: sampleArray) { case (acc, elem) => medianReductionFunction(acc, elem) }
    var pivot = getMedianPivot(sampleArray, median)

    exch(a, median.minz, lo) if (lo == pivot) pivot = median.minz
    exch(a, median.maxz, hi) if (hi == pivot) pivot = median.maxz
    //move pivot at position hi - 1
    exch(a, pivot, hi - 1)
    hi - 1
  }

  protected def partition(a: Array[T], lo: Int, hi: Int, pivotIndex: Int): Int = {
    var (i, j) = (lo, hi + 1) //left and right scan indices

    //removed bound checks
    def scan(index: Int, direction: Scan): Int = direction match {
      case Left => if (less(a(index), a(pivotIndex))) scan(index + 1, direction) else index
      case Right => if (index == pivotIndex || less(a(pivotIndex), a(index))) scan(index - 1, direction) else index
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
  case class Median (minz: Int, maxz: Int)
}