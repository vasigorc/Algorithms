package ca.vgorcinschi.algorithms_2

import scala.reflect.ClassTag

class Ex2_1_25[T : ClassTag : Ordering] extends BaseSort [T]{

  override def sort(a: Array[T]): Array[T] = {
    val N = a.length
    for(i <- 1 until N){
      bubble(i, a)
    }
    a
  }
  
  def bubble(i: Int, a: Array[T]): Unit = {
    val tmp: T = a(i)
    var j = i
    
    while (j > 0 && less(tmp, a(j-1))){
      a(j) = a(j-1)
      j-=1
    }
    if(j != i){
      a(j) = tmp
    }
  }
}
