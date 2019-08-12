package ca.vgorcinschi.algorithms2_4

trait MaxPQ [Key]{

  implicit protected val cmp: Ordering[_ >: Key]

  def insert(v: Key): Unit
  def max(): Key
  def delMax(): Key
  def isEmpty(): Boolean
  def size(): Int
}