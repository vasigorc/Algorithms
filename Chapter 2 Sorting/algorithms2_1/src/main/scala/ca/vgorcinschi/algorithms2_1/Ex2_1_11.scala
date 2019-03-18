package ca.vgorcinschi.algorithms2_1

import scala.reflect.ClassTag

/**
  * Exercise 2.1.11
  * Implement a version of shellsort that keeps the increment sequence in an array,
  * tather than computing it
 *
  * @author vgorcinschi
  */
class Ex2_1_11[T : ClassTag : Ordering] extends BaseSort[T]{

  override def sort(a: Array[T]): Array[T] = {
    val N = a.length
    val sequence: Array[Int] = sequenceStream(N).toArray //1, 4, 13, 40, 121, 364, 1093, ...

    for (gap <- sequence.reverse){
      for (i <- gap until N){
        var j = i
        while (j >= gap && less(a(j), a(j - gap))) {
          exch(a, j, j - gap)
          j = j - gap
        }
      }
    }
    a
  }

  def sequenceStream(N: Int)(implicit startIndex:Int = 1): Stream[Int] = {
    import ca.vgorcinschi.StreamOps

    Stream.iterate(startIndex)(h => 3*h +1).takeUntil(_ < N/3)
  }

  /**
    below method is introduced to support Ex2_2_12
    the gist is to shellsort only subarray.
    no return value - method violates referential transparency
   */
  def sort(a: Array[T], start: Int, end: Int): Unit ={
    val sequence: Array[Int] = sequenceStream(end-start).toArray //1, 4, etc

    def whileExch(j: Int, gap: Int):Unit = j match {
      case _ if j >= start+gap && less(a(j), a(j - gap)) =>
        exch(a, j, j-gap)
        whileExch(j-gap, gap)
      case _ =>
    }

    for (gap <- sequence.reverse){
      for (i <- start+gap to end){
        whileExch(i, gap)
      }
    }
  }
}
