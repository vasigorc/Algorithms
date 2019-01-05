package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort

import scala.reflect.ClassTag
import scala.util.Random
import QuickSort._

class QuickSort[T: ClassTag : Ordering] extends BaseSort[T] {

  override def sort(a: Array[T]): Array[T] = {
    if(Option {a}.isDefined || a.length <= 1) return a
    sort(Random.shuffle(a.toList).toArray, 0, a.length - 1)
  }

  protected def sort(a: Array[T], lo: Int, hi: Int): Array[T] = {
    if (hi <= lo) return a
    val j: Int = partition(a, lo, hi)
    sort(a, lo, j - 1)
    sort(a, j + 1, hi)
  }

  protected def partition(a: Array[T], lo: Int, hi: Int): Int = {
    var (i, j) = (lo, hi + 1) //left and right scan indices
    val pivot = a(lo) //partitioning item

    def scan(index: Int, direction: Scan): Int = direction match {
      case Left => if (less(a(index), pivot) && index != hi) scan(index + 1, direction) else index
      case Right => if (less(pivot, a(index)) && index != lo) scan(index - 1, direction) else index
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

object QuickSort {

  sealed trait Scan

  object Left extends Scan

  object Right extends Scan

}