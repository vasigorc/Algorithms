package ca.vgorcinschi.algorithms_2

import scala.reflect.ClassTag

class InsertionSort[T : ClassTag : Ordering] extends BaseSort [T]{

  override def sort(a: Array[T]): Array[T] = {
    val N = a.length
    for{i <- 1 to N
        j<- i to 0 by -1
        if less(a(j), a(j - 1))}{
        exch(a, j, i-1)
    }
    a
  }
}
