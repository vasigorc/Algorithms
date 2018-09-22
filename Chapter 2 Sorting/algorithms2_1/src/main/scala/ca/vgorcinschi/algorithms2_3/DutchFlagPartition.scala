package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort

trait DutchFlagPartition [T]{
  /*
    scala's self type, with this DutchFlagPartition can only be mixed-in to a class
    that extends BaseSort
   */
  this: BaseSort[T] =>

  def partition(pivotIndex: Int, a: Array[T]) :Unit = {
    val pivot = a(pivotIndex)
    var (smaller, equal, larger) = (0, 0, a.length) //scala way of declaring multiple vars on one line

    while (equal < larger){
      if(less(a(equal), pivot)){
        exch(a, smaller, equal)
        smaller += 1
        equal += 1
      } else if(less(pivot, a(equal))){
        exch(a, equal, larger)
        larger -= larger
      } else {
        equal += 1
      }
    }
  }
}
