package ca.vgorcinschi.algorithms_2

/**
  * Exercise 2.1.11
  * Implement a version of shellsort that keeps the increment sequence in an array,
  * tather than computing it
 *
  * @author vgorcinschi
  */
class Ex2_1_11[T <% Ordered[T]] extends BaseSort[T]{

  override def sort(a: Array[T]): Array[T] = {
    val N = a.length
    val sequence: Array[Int] = sequenceStream(N).toArray //1, 4, 13, 40, 121, 364, 1093, ...

    def tryExch(j: Int, gap: Int):Unit = j match {
      case _ if j >= gap && less(a(j), a(j - gap)) =>
        exch(a, j, j-gap)
        tryExch(j-gap, gap)
      case _ =>
    }

    for (gap <- sequence.reverse){
      for (i <- gap until N){
        tryExch(i, gap)
      }
    }
    a
  }

  def sequenceStream(N: Int)(implicit startIndex:Int = 1): Stream[Int] = {
    import ca.vgorcinschi.StreamOps

    Stream.iterate(startIndex)(h => 3*h +1).takeUntil(_ < N/3)
  }

  /*
    below method is introduced to support Ex2_2_12
    the gist is to shellsort only subarray.
    no return value - method violates referential transparency
   */
  def sort(a: Array[T], start: Int, end: Int): Unit ={
    val N = end
    val sequence: Array[Int] = sequenceStream(end-start).toArray //1, 4, etc

    def tryExch(j: Int, gap: Int):Unit = j match {
      case _ if j >= gap && less(a(j), a(j - gap)) =>
        exch(a, j, j-gap)
        tryExch(j-gap, gap)
      case _ =>
    }

    for (gap <- sequence.reverse){
      for (i <- start+gap to end){ //TODO: think over the looping logic
        tryExch(i, gap)
      }
    }
  }
}
