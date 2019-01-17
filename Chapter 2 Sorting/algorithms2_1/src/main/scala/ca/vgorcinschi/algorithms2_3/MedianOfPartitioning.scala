package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort
import ca.vgorcinschi.algorithms2_3.QuickSort.{Left, Right, Scan}

import scala.util.Random

trait MedianOfPartitioning [T] {

  this: BaseSort[T] =>

  protected val MEDIAN_OF: Int

  protected def getSample(a: Array[T], medianOf: Int): Array[T] = {
    val sampleArrayOffset = Random.nextInt(a.length - medianOf)
    a.slice(sampleArrayOffset, sampleArrayOffset + medianOf)
  }

  /**
    * Mutate the main array to have at its head the smallest value from sample
    * array and at its last index the biggest value from sample array
    * @param a - target array
    * @param sampleArray - size should be equal to MEDIAN_OF*/
  def patchArray(a: Array[T], lo: Int, hi: Int, sampleArray: Array[T]): Unit = {
    val (minVal, maxVal) = ((sampleArray.head, sampleArray.head) /: sampleArray) { case ((tempMin, tempMax), current) =>
      (min(tempMin, current), max(tempMax, current))
    }
    exch(a, a.indexOf(minVal), lo)
    exch(a, a.indexOf(maxVal), hi)
  }

  protected def partition(a: Array[T], lo: Int, hi: Int, pivot: T): Int = {
    var (i, j) = (lo, hi + 1) //left and right scan indices

    //removed bound checks
    def scan(index: Int, direction: Scan): Int = direction match {
      case Left => if (less(a(index), pivot)) scan(index + 1, direction) else index
      case Right => if (less(pivot, a(index))) scan(index - 1, direction) else index
    }

    while (i < j) {
      //scan right, scan left, check for scan complete, and exchange
      i = scan(i + 1, Left)
      j = scan(j - 1, Right)
      if (i < j) exch(a, i, j)
    }

    exch(a, a.indexOf(pivot), j) // put pivot into a(j)
    j // with a(lo..j-1) <= a(j) <= a(j+1..hi)
  }
}