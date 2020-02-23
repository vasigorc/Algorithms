package ca.vgorcinschi.algorithms2_1

import scala.reflect.ClassTag

/**
  * Ex 2.1.25 Insertion sort without exchanges. Develop an implementation of insertion sort that moves larger elements
  * to the right one position with one array access per entry rather then using exch().
  * @tparam T
  */
class Ex2_1_25[T : ClassTag : Ordering] extends BaseSort [T]{

  override def sort(a: Array[T]): Array[T] = {
    val N = a.length
    for(i <- 1 until N){
      bubble(i, a)
    }
    a
  }
  
  private def bubble(i: Int, a: Array[T]): Unit = {
    val tmp: T = a(i)
    var j = i
    
    while (j > 0 && less(tmp, a(j-1))){
      a(j) = a(j-1)
      j-=1
    }
    a(j) = tmp
  }
}
