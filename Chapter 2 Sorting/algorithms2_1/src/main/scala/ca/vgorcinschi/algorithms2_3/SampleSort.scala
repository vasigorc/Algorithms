package ca.vgorcinschi.algorithms2_3

import scala.reflect.ClassTag
import ca.vgorcinschi.ArrayOps

import Ordered._

//noinspection SpellCheckingInspection
class SampleSort[T: ClassTag : Ordering](val sampleSize: Int = 30) extends QuickSort[T] {

  import SearchTree._

  override def sort(a: Array[T]): Array[T] = {
    require(a != null, "Passed-in array should not be null")
    sortHelper(a)
  }

  private def sortHelper(a: Array[T]): Array[T] = {
    //if the array is shorter then the sampling - sort it with Quicksort
    if (a.length <= sampleSize) return super.sort(a)

    /*
      just the indices for the sample array.
      also required later for figuring out the nonPartitionedRemainder of the array
     */
    val sampleArrayIndices: Array[Int] = a.subArrayOfSize(sampleSize)
    val sampleArray: Array[T] = sampleArrayIndices map (a(_))

    val pivot: Int = partition(sampleArray, 0, sampleArray.length - 1)
    val searchTree: SearchTree = buildTree(sampleArray, pivot)
    val nonPartitionedRemainder = a.slice(0, sampleArrayIndices.head) ++ a.slice(sampleArrayIndices.last, a.length - 1)
    val finalTree = (searchTree /: nonPartitionedRemainder) ((tree, elem) => tree.nest(elem))
    finalTree.arrays() flatMap sort
  }

  private class SearchTree(lt: Array[T], median: Array[T], gt: Array[T]) {
    //hear median is guaranteed to be non null and non empty based off the partitioning in sortHelper
    private val pivot: T = median.head

    def nest(value: T): SearchTree = {
      if (value < pivot) SearchTree(lt :+ value, median, gt)
      if (value > pivot) SearchTree(lt, median, gt :+ value)
      else SearchTree(lt, median :+ value, gt)
    }

    def arrays(): Array[Array[T]] = Array(lt, median, gt)
  }

  object SearchTree {
    def buildTree(sample: Array[T], pivot: Int): SearchTree = {
      //do not look beyond pivot since sample is guaranteed to be partitioned
      val lastLessThenValueIndex: Int = sample.lastIndexWhere(_ < sample(pivot), pivot)
      //only look from pivot and up
      val medianAndGt: (Array[T], Array[T]) = sample.slice(pivot, sample.length - 1) partition (_ == sample(pivot))
      val lt = sample.slice(0, lastLessThenValueIndex)
      SearchTree(lt, medianAndGt._1, medianAndGt._2)
    }

    def apply(lt: Array[T], median: Array[T], gt: Array[T]): SearchTree = new SearchTree(lt, median, gt)
  }qZZ
}