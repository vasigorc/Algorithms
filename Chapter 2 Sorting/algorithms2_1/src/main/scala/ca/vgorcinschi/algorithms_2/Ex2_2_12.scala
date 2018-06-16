package ca.vgorcinschi.algorithms_2

import ca.vgorcinschi.gd

import scala.reflect.ClassTag
/**
  * Sublinear extra space. Develop a merge implementation that reduces the extra
  * space requirements to max(M, N/M), based on the following idea, Divide the array
  * into N/M blocks of size M (for simplicity, assume that N is a multiple of M). Then,
  * (i) considering the blocks as items with their first key as the sort key, sort them using
  * selection sort; and (ii) run through the array merging the first block with the second, then
  * the second with the third, and so fourth.
  *
  * to me it sounds like bottom-up mergesort implementation with the exception that all sorts
  * on subarrays are done using shellsort
  *
  * @tparam T - bound type for any Ordered (Comparable) type
  */
class Ex2_2_12[T : ClassTag : Ordering] extends MergeSort[T]{

  override def sort(a: Array[T]): Array[T] = {
    val N = a.length
    aux = new Array[T](a.length)

    val greatestDivisor = gd(N)
    val shellSort = new Ex2_1_11[T]

    //sort part
    for(i <- 0 to N-greatestDivisor by greatestDivisor){
      shellSort.sort(a, i, (i+greatestDivisor)-1)//need to do -1 because we're 0 based index
    }

    //merge part, need to do *2 because merge method expects a mid parameter
    def tryMerge(startIndex: Int, gd: Int): Unit ={
      val twos = 2*gd

      if((startIndex + twos) < N){
        val end = startIndex+twos-1
        val mid = (end + startIndex)/2
        merge(a, startIndex, mid, end)
        tryMerge(startIndex+twos, gd) // continue to try to merge while there are more chunks (move startIndex past end of last chunk)
      } else { //bottom case we only have one subarray left
        merge(a, startIndex - twos, startIndex - 1, N-1)
      }
    }

    //we need to expand the chunks to merge while we haven't passed the middle of the original array
    var currentGD = greatestDivisor
    while (currentGD < N/2){
      tryMerge(0, currentGD)
      currentGD = currentGD * 2
    }
    a
  }

  /*
    we will take the greatest valid divisor as the splitterator for our array
   */
  def sublinearIndexes(arrayLength: Int):Set[Int] ={
    import ca.vgorcinschi._
    //safe to do (0) as the underlying Stream is guaranteed to be at least Stream(1)
    (0 to arrayLength).filter(i => i % descedingValidDivisors(i).take(1)(0) == 0).toSet
  }
}
