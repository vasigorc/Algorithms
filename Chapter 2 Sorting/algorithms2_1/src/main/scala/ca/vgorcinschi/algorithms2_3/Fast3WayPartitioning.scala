package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort

import scala.reflect.ClassTag
import scala.util.Random

/**
  * Exercise 2.3.22 Fast 3-way-partitioning. (J. Bentley and D. McIlroy) Implement an entropy-optimal sort based on
  * keeping items with equal keys at bpth left and right ends of the subarray. Maintain indices p and q such that
  * a(lo..p-1) and a(q+1..hi) are all equal to a(lo), and index i such that a(p..i-1) are all less than a(lo), and
  * an index j such that a(j+1..q) are all greater than a(lo). Add to the inner partitioning loop code to swap
  * a(i) with a(p) (and increment p) if it is equal to v and swap a(j) with a(q) (and decrement q) if it is equal
  * to v before the usual comparisons of a(i) and a(j) with v. After the partitioning loop has terminated, add code
  * to swap the items with equal keys into position. Note: This code complements the code given in the text, in the
  * sense that it does extra swaps for keys equal to the partitioning item's key, while the code in the text does extra
  * swaps for keys that are not equal to the partitioning item's key.
  * @param classTag$T - provides runtime information about generic type, required for having array of ordered items
  * @param ordering$T - makes sure that generic type is [[Ordered]]
  * @tparam T - generic type
  */
class Fast3WayPartitioning[T: ClassTag : Ordering] extends BaseSort[T] {

  override def sort(a: Array[T]): Array[T] = {
    val shuffledArray = Random.shuffle(a.toSeq).toArray
    sort(shuffledArray, 0, a.length - 1)
  }

  private def sort(array: Array[T], lo: Int, hi: Int): Array[T] = {
    if (hi <= lo) return array
    val pivot: T = array(lo)
    var (p, i, q, j) = (lo, lo + 1, hi, hi)

    //partition loop
    while (i < j) {
      //first change for the left edge (the i part)
      val iValue = array(i)
      if (less(iValue, pivot)) i += 1
      else if (less(pivot, iValue)) {
        exch(array, i, j)
        j -= 1
      }
      else {
        p += 1
        exch(array, p, i)
        i += 1
      }
      if (i < j) {
        val jValue = array(j)
        //then compare for the right edge (the j part)
        if (less(pivot, jValue)) j -= 1
        else if (less(jValue, pivot)) {
          exch(array, i, j)
          i += 1
        }
        else {
          exch(array, j, q)
          q -= 1
          j -= 1
        }
      }
    }
    ???
  }
}