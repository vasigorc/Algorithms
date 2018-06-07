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

    def tryExch(j: Int, h: Int):Unit = j match {
      case _ if j >= h && less(a(j), a(j - h)) =>
        exch(a, j, j-h)
        tryExch(j-h, h)
      case _ =>
    }

    for (h <- sequence.reverse){
      for (i <- h until N){
        tryExch(i, h)
      }
    }
    a
  }

  def sequenceStream[T <: Ordered[T]](N: Int): Stream[Int] = {
    import ca.vgorcinschi.StreamOps

    Stream.iterate(1)(h => 3*h +1).takeUntil(_ < N/3)
  }
}
