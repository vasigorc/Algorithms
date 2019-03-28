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
    qSort(shuffledArray)
  }

  private def qSort(array: Array[T]): Array[T] = {
    val (lo, hi) = (0, array.length - 1)
    if (hi <= lo) return array

    val pivot = array.head

    def loop(source: Array[T], accumulator: ArraySections): ArraySections = {
      if (source.isEmpty) return accumulator
      val tail = source.tail
      source.head match {
        case v if v == pivot => loop(tail, accumulator copy(equ = accumulator.equ :+ pivot))
        case v if less(v, pivot) => loop(tail, accumulator copy(lt = accumulator.lt :+ v))
        case v @ _ => loop(tail, accumulator copy(gt = accumulator.gt :+ v))
      }
    }

    val arraySections = loop(array.tail, ArraySections(equ = Array(pivot)))
    qSort(arraySections.lt) ++ arraySections.equ ++ qSort(arraySections.gt)
  }

  case class ArraySections(lt: Array[T] = Array.empty, equ: Array[T], gt: Array[T] = Array.empty)
}