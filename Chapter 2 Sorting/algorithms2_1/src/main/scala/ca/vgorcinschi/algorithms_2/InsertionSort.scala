package ca.vgorcinschi.algorithms_2

import scala.reflect.ClassTag

class InsertionSort[T : ClassTag : Ordering] extends BaseSort [T]{

  override def sort(a: Array[T]): Array[T] = {
    val N = a.length
    for(i <- 1 until N) {
      var j = i
      while (j > 0 && less(a(j), a(j - 1))) {
          exch(a, j, j - 1)
        j-=1
      }
    }
    a
  }
}
