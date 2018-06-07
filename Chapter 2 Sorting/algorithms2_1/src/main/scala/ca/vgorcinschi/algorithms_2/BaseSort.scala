package ca.vgorcinschi.algorithms_2

import edu.princeton.cs.algs4.StdOut

trait BaseSort {

  def sort[T](a: Array[T])(implicit order: T => Ordered[T]):Array[T]

  def less[T](v: T, w: T)(implicit order: T => Ordered[T]):Boolean = order(v) < w

  def exch[T](a: Array[T], i: Int, j: Int)(implicit order: T => Ordered[T]): Unit = {
    val t = a(i)
    a(i) = a(j)
    a(j) = t
  }

  def show[T](a: Array[T])(implicit order: T => Ordered[T]): Unit = a.foreach(t => StdOut.print(t+" ")); StdOut.println()

  def isSorted[T](a: Array[T])(implicit order: T => Ordered[T]): Boolean = {
    for(i <- 1 to a.length){
      if(less(a(i), a(i-1))) false
    }
    true
  }
}
