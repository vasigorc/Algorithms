package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort

import scala.util.Random

trait MedianOfPartitioning [T] {

  this: BaseSort[T] =>

  protected val MEDIAN_OF: Int

  protected def getSample(a: Array[T], medianOf: Int): Array[T] = {
    //TODO what if a.length is < medianOf
    val sampleArrayOffset = Random.nextInt(a.length - medianOf)
    a.slice(sampleArrayOffset, sampleArrayOffset + medianOf)
  }

  /**
    * Mutate the main array to have at its head the smallest value from sample
    * array and at its last index the biggest value from sample array
    * @param a - target array
    * @param sampleArray - size should be equal to {@link MEDIAN_OF}
    */
  def patchArray(a: Array[T], lo: Int, hi: Int, sampleArray: Array[T]): Unit = {
    val (minVal, maxVal) = ((sampleArray.head, sampleArray.head) /: sampleArray) { case ((tempMin, tempMax), current) =>
      (min(tempMin, current), max(tempMax, current))
    }
    exch(a, a.indexOf(minVal), lo)
    exch(a, a.indexOf(maxVal), hi)
  }
}