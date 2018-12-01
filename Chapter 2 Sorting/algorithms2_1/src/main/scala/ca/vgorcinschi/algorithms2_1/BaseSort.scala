package ca.vgorcinschi.algorithms2_1

import edu.princeton.cs.algs4.StdOut

abstract class BaseSort [T : Ordering]{

  def sort(a: Array[T]):Array[T]

  def less(v: T, w: T):Boolean = {
    import Ordered._
    v < w
  }

  def exch(a: Array[T], i: Int, j: Int): Unit = {
    val t = a(i)
    a(i) = a(j)
    a(j) = t
  }

  def show(a: Array[T]): Unit = a.foreach(t => StdOut.print(t+" ")); StdOut.println()

  def isSorted(a: Array[T]): Boolean = {
    for(i <- 1 to a.length){
      if(less(a(i), a(i-1))) false
    }
    true
  }
}