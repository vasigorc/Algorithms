package ca.vgorcinschi.algorithms2_2

import scala.collection.immutable.Queue
import scala.reflect.ClassTag

class Ex2_2_15[T : ClassTag : Ordering] {
  import ca.vgorcinschi._

  def sort(a: Array[T]):Queue[T]= {

    def internal(a1: Array[Queue[T]]):Queue[T] = {
      if(a1.length == 0) Queue()
      else if(a1.length == 1) a1.head
      else {
        val result = a1.grouped(2).map{case (a1: Array[Queue[T]]) =>
          if(a1.length ==1) a1(0) else a1(0).mergeQueue(a1(1))}.toArray
        internal(result)
      }
    }

    val quArray: Array[Queue[T]] = a.map(t => Queue(t))
    internal(quArray)
  }
}
