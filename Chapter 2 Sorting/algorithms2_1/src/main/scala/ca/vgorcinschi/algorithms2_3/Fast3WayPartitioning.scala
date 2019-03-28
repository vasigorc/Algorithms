package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort

import scala.reflect.ClassTag
import scala.util.Random

/**
  * Exercise 2.3.22 Fast 3-way-partitioning. (J. Bentley and D. McIlroy) Implement an entropy-optimal sortHelper based on
  * keeping items with equal keys at bpth left and right ends of the subarray. Maintain indices p and q such that
  * a(lo..p-1) and a(q+1..hi) are all equal to a(lo), and index i such that a(p..i-1) are all less than a(lo), and
  * an index j such that a(j+1..q) are all greater than a(lo). Add to the inner partitioning loop code to swap
  * a(i) with a(p) (and increment p) if it is equal to v and swap a(j) with a(q) (and decrement q) if it is equal
  * to v before the usual comparisons of a(i) and a(j) with v. After the partitioning loop has terminated, add code
  * to swap the items with equal keys into position. Note: This code complements the code given in the text, in the
  * sense that it does extra swaps for keys equal to the partitioning item's key, while the code in the text does extra
  * swaps for keys that are not equal to the partitioning item's key.
  *
  * @param classTag$T - provides runtime information about generic type, required for having array of ordered items
  * @param ordering$T - makes sure that generic type is [[Ordered]]
  * @tparam T - generic type
  */
class Fast3WayPartitioning[T: ClassTag : Ordering] extends BaseSort[T] {

  override def sort(a: Array[T]): Array[T] = {
    val shuffledArray = Random.shuffle(a.toSeq).toArray
    sortHelper(shuffledArray)
  }

  private def sortHelper(array: Array[T]): Array[T] = {
    val (lo, hi) = (0, array.length - 1)
    if (hi <= lo) return array
    val pivot: T = array(lo)
    var (p, i, j) = (lo, lo + 1, hi)
    var q = if (equal(array, lo, hi)) hi else array.length

    //1. partition phase
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
        //then compare for the right edge (the j part)
        val jValue = array(j)
        if (less(pivot, jValue)) j -= 1
        else if (less(jValue, pivot)) {
          exch(array, i, j)
          i += 1
        }
        else {
          q -= 1
          exch(array, j, q)
          j -= 1
        }
      }
    }

    i = adjustLtThreshold(array, i, p)
    j = i + 1
    //2. swap phase
    //we don't need to move pivot after "less than" part if pivot is the smallest element
    if (less(array(p + 1), pivot)) {
      while (p >= lo) {
        exch(array, i, p)
        p -= 1
        i -= 1
      }
      //problem if q == hi
      while (q <= hi) {
        exch(array, j, q)
        j += 1
        q += 1
      }
      val lt = array.slice(lo, i + 1)
      val eq = array.slice(i + 1, j)
      val gt = array.slice(j, hi + 1)
      return sortHelper(lt) ++ eq ++ sortHelper(gt)
    }
    val eq = array.slice(lo, p + 1) ++ array.slice(q + 1, hi + 1)
    val gt = array.slice(p + 1, q + 1)
    eq ++ sortHelper(gt)
  }

  private def adjustLtThreshold(array: Array[T], i: Int, p: Int) = {
    if (i -1 == p) i
    else if (less(array, i - 1, i)) i - 1 else i
  }
}