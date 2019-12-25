package ca.vgorcinschi.algorithms2_4

// FIXME change return type of both max and delMax() to return Option[Key]
// this will remove a lot of ugliness from the implementation
trait MaxPQ[Key] {

  implicit protected val cmp: Ordering[_ >: Key]
  //nr. of keys present in the queue
  protected var N: Int = 0

  def insert(v: Key): Unit

  def max(): Key

  def delMax(): Key

  //is the Priority Queue empty?
  def isEmpty: Boolean = N == 0

  def size(): Int = N
}