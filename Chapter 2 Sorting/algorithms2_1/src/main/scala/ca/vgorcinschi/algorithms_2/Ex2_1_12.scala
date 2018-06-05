package ca.vgorcinschi.algorithms_2

import scala.collection.mutable.ListBuffer

/**
  * From "Introduction to the Art of Programming Using Scala":
  * Shell Sort is also called the "dimishing gap sort". Introduced in 1959 by Donald Shell iy was one of the first
  * general sort algorithms that is faster than O(N^2).
  *
  * Its basic idea is a bit counterintuitive. It performs an insertion sort repeatedly on different subsets of the full
  * array. To start with, the subsets are taken to be groups of elements that are widely spaced. The spacing between the
  * elements in each subset is called the "gap". The gap is decreased in size for each run through the sort.
  *
  * Exercise 2.1.12 Instrument shellsort to print the number of compares divided by the array size for each increment.
  * Write a test client that tests the hypothesis that this number is a small constant, by sorting arrays of random Double
  * values using array sizes that are increasing powers of 10, starting at 100.
 *
  * @author vgorcinschi
  */
class Ex2_1_12 extends BaseSort {

  var nrOfCompares:Int = 0
  var previousNrOfCompares: Int = nrOfCompares
  val allRatios = ListBuffer[Double]()

  def reevaluateCompares(gap: Int, N: Int) = {
    val ratio = (nrOfCompares.toDouble-previousNrOfCompares)/N
    println(s"Ratio of compares/array size is $ratio for gap $gap")
    allRatios+=ratio
    previousNrOfCompares = nrOfCompares
  }

  override def sort[T <% Ordered[T]](a: Array[T]): Array[T] = {
    val N = a.length
    var gap = N/2
    while (gap>=1){
      for (j <- gap until N){
        var i = j-gap//the "look back at" index
        val temp = a(j)
        if(a(i)>temp) nrOfCompares+=1//if needed only to count compares
        while(i>=0 && a(i)>temp){
          a(i+gap) = a(i)
          i-=gap
        }
        a(i+gap) = temp//re-insert the "current benchmark" temp value
      }//end for
      reevaluateCompares(gap, N)
      gap=(gap/2.2).round.toInt
    }//end outer while
    a
  }
}
