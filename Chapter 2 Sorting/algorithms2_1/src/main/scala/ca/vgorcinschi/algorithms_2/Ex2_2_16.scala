package ca.vgorcinschi.algorithms_2

import scala.reflect.ClassTag

/*
  Exercise 2.2.16 Natural mergesort. Write a version of bottom-up mergesort
  that takes advantage of order in the array by proceeding as follows each
  time it needs to find two arrays to merge: find a sorted subarray (by incrementing a pointer until finding an
  entry that is smaller that its predecessor in the array), then find the next, then merge them. Analyze the running
  time if this algorithm in terms of array size and the number of maximal increasing sequences in the array.
 */
class Ex2_2_16[T : ClassTag : Ordering] extends MergeSort[T] {

  override def sort(a: Array[T]): Array[T] = {
    aux = new Array[T](a.length)

    var thereIsMore = true
    var sfa, efa, esa: Int = 0
    val N = a.length - 1

    import Ordered._

    def maxValue(endSubArray: Int, startSubArray: Int): T = {
      val criteriaIndex = if (endSubArray - 1 >= startSubArray) endSubArray - 1 else startSubArray
      val result = if(a(startSubArray) > a(criteriaIndex)) a(startSubArray) else a(criteriaIndex)
      result
    }

    while (thereIsMore){
      while (efa < N){
        while (efa < N && maxValue(efa, sfa) <= a(efa))
          efa += 1

        esa = efa

        while (esa < N && maxValue(esa, efa) <= a(esa))
          esa += 1

        if(efa != esa)
          merge(a, sfa, math.max(efa - 1, 0), esa - 1)
        else
          thereIsMore = false

        sfa = esa
        efa = sfa
      }
      if(thereIsMore){
        efa = 0
        sfa = 0
        esa = 0
      }
    }
    a
  }
}