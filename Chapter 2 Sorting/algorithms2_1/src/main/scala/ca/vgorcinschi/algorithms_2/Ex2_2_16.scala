package ca.vgorcinschi.algorithms_2

import scala.reflect.ClassTag

class Ex2_2_16[T : ClassTag : Ordering] extends MergeSort[T] {

  override def sort(a: Array[T]): Array[T] = ???
}
