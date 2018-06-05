package ca.vgorcinschi.algorithms_2

import edu.princeton.cs.algs4.StdOut

trait BaseSort {

  def sort[T <% Ordered[T]](a: Array[T]):Array[T]

  def less[T](v: T, w: T)(implicit order: T => Ordered[T]):Boolean = order(v) < w

  def exch[T <% Ordered[T]](a: Array[T], i: Int, j: Int) = {
    val t = a(i)
    a(i) = a(j)
    a(j) = t
  }

  def show[T <% Ordered[T]](a: Array[T]) = a.foreach(t => StdOut.print(t+" ")); StdOut.println()

  def isSorted[T <% Ordered[T]](a: Array[T]) = {
    for(i <- 1 to a.length){
      if(less(a(i), a(i-1))) false
    }
    true
  }
}
