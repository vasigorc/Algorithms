package ca.vgorcinschi.algorithms_2

/**
  * Exercise 2.1.11
  * Implement a version of shellsort that keeps the increment sequence in an array,
  * tather than computing it
 *
  * @author vgorcinschi
  */
class Ex2_1_11 extends BaseSort{

  override def sort[T <% Ordered[T]](a: Array[T]): Array[T] = {
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

  def sequenceStream[T <: Ordered[T]](N: Int): Stream[Int] = {
    import ca.vgorcinschi.StreamOps

    Stream.iterate(1)(h => 3*h +1).takeUntil(_ < N/3)
  }
}
