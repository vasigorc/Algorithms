package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort

import scala.reflect.ClassTag

class Fast3WayPartitioning[T: ClassTag : Ordering] extends BaseSort[T] with DutchFlagPartition[T]{

  override def sort(a: Array[T]): Array[T] = ???

  override def partition(pivotIndex: Int, a: Array[T]): Unit = ???
}