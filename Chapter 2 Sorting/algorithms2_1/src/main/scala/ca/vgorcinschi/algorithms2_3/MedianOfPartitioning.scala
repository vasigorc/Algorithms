package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort
import ca.vgorcinschi.algorithms2_1.BaseSort.{max, min}

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
    * @param sampleArray - size should be equal to @{link MEDIAN_OF}
    */
  protected def patchArray(a: Array[T], sampleArray: Array[T]) (implicit ev: T <:< Ordering[T]): Unit = {
    val (minVal, maxVal) = ((sampleArray.head, sampleArray.head) /: sampleArray) { case ((tempMin, tempMax), current) =>
      (min(tempMin, current), max(tempMax, current))
    }
    exch(a, a.indexOf(minVal), 0)
    exch(a, a.indexOf(maxVal), a.length - 1)
  }
}