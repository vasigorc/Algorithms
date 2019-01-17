package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort
import ca.vgorcinschi.algorithms2_3.QuickSort.{Left, Right, Scan}

trait MedianOfPartitioning [T] {

  this: BaseSort[T] =>

  protected val MEDIAN_OF: Int

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

    /*
      We might need to have a better way to track change of pivot index
      than a potential N moves using `indexOf`. One approach is to skip
      hi - 1 in `scan` and do exch(a, hi -1, j) in the end
     */
    val nextPivotIndex = if (less(a(i), a(j))) j else i
    exch(a, pivotIndex, nextPivotIndex) // put pivot into a(j)
    nextPivotIndex // with a(lo..j-1) <= a(j) <= a(j+1..hi)
  }
}