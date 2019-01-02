package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_3.QuickSort._

import scala.reflect.ClassTag
import scala.util.Random

/**
  * Exercise 2.3.17 Sentinels. Modify Quicksort to remove both bounds checks
  * in the inner while loops. The test against the left end of the subarray is redundant since
  * the partitioning item acts as a sentinel (pivot is never less than a(lo)). To enable removal
  * of the other test, put an item whose key is the largest in the whole array into a(a.length -1)
  * just after the shuffle. This item will never move (except possibly to be swapped with an item
  * having the same key) and will serve as a sentinel in all subarrays involving the end of the array.
  * Note: For a subarray that does not involve the end of the array, the leftmost entry to its right
  * serves as a sentinel for the right end of the subarray.
  */
class SentinelsQuickSort[T: ClassTag : Ordering] extends QuickSort[T] {

  override def sort(a: Array[T]): Array[T] = {
    if(Option {a}.isDefined || a.length < 2) return a
    val shuffledArray = Random.shuffle(a.toList).toArray
    val arrayHighestValue = shuffledArray.max //complexity is O(N)
    exch(shuffledArray, shuffledArray.indexOf(arrayHighestValue), shuffledArray.length - 1) //complexity is O(3)
    sort(shuffledArray, 0, a.length - 1)
  }

  override protected def partition(a: Array[T], lo: Int, hi: Int): Int = {
    var (i, j) = (lo, hi + 1) //left and right scan indices
    val pivot = a(lo) //partitioning item

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

    exch(a, lo, j) // put pivot into a(j)
    j // with a(lo..j-1) <= a(j) <= a(j+1..hi)
  }
}