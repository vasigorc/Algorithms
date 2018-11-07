package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort

import scala.reflect.ClassTag
import scala.util.Random

class QuickSort[T: ClassTag : Ordering] extends BaseSort[T] {

  override def sort(a: Array[T]): Array[T] = {
    assert(a.nonEmpty, s"${getClass.getSimpleName}.sort expects a non empty array as argument")
    sort(Random.shuffle(a).toArray, 0, a.length - 1)
  }

  private def sort(a: Array[T], lo: Int, hi: Int): Array[T] = {
    if (hi <= lo) return a
    val j: Int = partition(a, lo, hi)
    sort(a, lo, j - 1)
    sort(a, j + 1, hi)
  }

  private def partition(a: Array[T], lo: Int, hi: Int): Int = {
    var (i, j) = (lo, hi + 1) //left and right scan indices
    val pivot = a(lo) //partitioning item
    while (i < j) {
      //scan right, scan left, check for scan complete, and exchange
      Stream.iterate(i)(i+=1).takeWhile(iter => i != hi || less(a(iter), pivot))
      Stream.iterate(j)(j-=1).takeWhile(iter => j != lo || less(pivot, a(iter)))
      if(i < j) exch(a, i, j)
    }
    exch(a, lo, j) // put pivot into a(j)
    j // with a(lo..j-1) <= a(j) <= a(j+1..hi)
  }
}
