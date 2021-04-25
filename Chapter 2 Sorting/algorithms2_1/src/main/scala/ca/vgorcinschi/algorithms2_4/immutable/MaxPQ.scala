package ca.vgorcinschi.algorithms2_4.immutable

import scala.language.higherKinds
import scala.reflect.ClassTag

sealed trait MaxPQ[+U] extends Iterable [U] {

  self =>

  type Contravariant[K >: U] <: MaxPQ[K]
  type Invariant[_] <: MaxPQ[U]

  /** pointer to last element in the PQ
   */
  val N: Int

  /** @param v - value intended to be added
   * @tparam K - type parameter of the new PQ
   * @return new PQ containing all values of this + new value
   */
  def insert[K >: U: Ordering: ClassTag](v: K): Contravariant[K]

  def max(): Option[U]

  /** @return tail of this MaxPQ
   */
  def delMax(): Invariant[_]

  override def size: Int = N

  override def isEmpty: Boolean = N == 0

  override def nonEmpty: Boolean = !self.isEmpty
}

abstract class AbstractMaxPQ[U: Ordering] extends MaxPQ[U]