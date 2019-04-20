package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.InsertionSort

import scala.reflect.ClassTag

/**
  * Exercise 2.3.23 Turkey's ninther. Add to your implementation from Exercise 2.3.22 code to use
  * the Turkey's ninther to compute the partitioning item - choose three sets of three items, take
  * the median of each, then use the median of the three medians of the three medians as the partitioning
  * item. Also, add a cutoff to insertion sort for small subarrays.
  *
  * @param classTag$T - provides runtime information about generic type, required for having array of ordered items
  * @param ordering$T - makes sure that generic type is [[Ordered]]
  * @tparam T - generic type
  */
class TurkeysNinther[T: ClassTag : Ordering] extends FastWayPartitioning[T] {

  import MedianOfPartitioning._
  import ca.vgorcinschi.ArrayOps

  val insertionSort = new InsertionSort[T]

  override protected def cutoffPredicate: Array[T] => Boolean = array => array.length < 9

  override protected def cutoffAction: Array[T] => Array[T] = array => insertionSort.sort(array)

  override protected def pivotSwap(array: Array[T]): Unit = {
    val (lo, hi) = (0, array.length - 1)
    val partitionSize = hi / 3

    def curriedSubArraySize: (Int, Int) => Array[Int] = (from, to) => array.subArrayOfSize(3, from, to)

    val firstTripple: Array[Int] = curriedSubArraySize(lo, partitionSize)
    val secondTripple: Array[Int] = curriedSubArraySize(partitionSize, 2 * partitionSize)
    val lastTripple: Array[Int] = curriedSubArraySize(2 * partitionSize, hi)

    val medianIndex = medianOf(array,
      Array(medianOf(array, firstTripple),
        medianOf(array, secondTripple),
        medianOf(array, lastTripple)))

    exch(array, lo, medianIndex)
  }

  def medianOf(targetArray: Array[T], tripple: Array[Int]): Int = {

    def medianReductionFunction = (median: MinMax, index: Int) => {
      var newMedian = median
      if (less(targetArray, index, median.indexOfMinValue)) newMedian = median copy (indexOfMinValue = index)
      if (less(targetArray, median.indexOfMaxValue, index)) newMedian = median copy (indexOfMaxValue = index)
      newMedian
    }

    val minMax: MinMax = (MinMax(tripple.head, tripple.head) /: tripple.tail) { case (acc, elem) => medianReductionFunction(acc, elem) }
    getMedian(tripple, minMax)
  }
}