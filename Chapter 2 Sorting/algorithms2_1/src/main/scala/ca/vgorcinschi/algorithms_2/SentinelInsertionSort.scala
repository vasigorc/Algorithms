package ca.vgorcinschi.algorithms_2

import scala.reflect.ClassTag

/*
  Ex 2.1.24
  Develop an implementation of insertion sort that eliminates the j>0 test in the inner loop
   by first putting the smaller item into position. Use SortCompare to evaluate the effectiveness
   of doing so. Note: it is often possible to avoid an index-out-bound test in this way - the element that
   enabes the test to be eliminated is known as sentinel
 */
class SentinelInsertionSort[T : ClassTag : Ordering] extends BaseSort [T]{

  override def sort(a: Array[T]): Array[T] = {
    val N = a.length

    //put smallest element in position
    var exchanges = 0
    for{i <- N-1 until 0 by -1
        if less(a(i), a(i-1))}{
      exch(a, i, i-1)
      exchanges+=1
    }
    if(exchanges == 0) a
    for(i <- 2 until N){
      val tmp = a(i)
      var j = i
      while (less(tmp, a(j-1))){
        a(j) = a(j-1)
        j-=1
      }
      a(j) = tmp
    }
    a
  }
}
