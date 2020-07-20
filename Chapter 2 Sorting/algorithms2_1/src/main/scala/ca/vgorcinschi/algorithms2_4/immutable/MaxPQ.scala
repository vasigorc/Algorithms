package ca.vgorcinschi.algorithms2_4.immutable

import scala.language.higherKinds
import scala.reflect.ClassTag

sealed trait MaxPQ[+U] {

  type Contravariant[K >: U] <: MaxPQ[K]
  type Nonvariant[_] <: MaxPQ[U]
  /**
   * pointer to last element in the PQ
   * no longer needs to be protected since it is a val
   * as compared to [[ca.vgorcinschi.algorithms2_4.MaxPQ.N]]
   */
  val N: Int

  /**
   *
   * @param v - value intended to be added
   * @tparam K - type parameter of the new PQ
   * @return new PQ containing all values of this + new value
   */
  def insert[K >: U : Ordering : ClassTag](v: K): Contravariant[K]

  def max(): Option[U]

  /**
   * remove the max element if at least one
   * element is present and a new instance of
   * this PQ without the max element
   * @return
   */
  def delMax(): (Option[U], Nonvariant[_])

  def isEmpty: Boolean = N == 0

  def size(): Int = N
}

abstract class AbstractMaxPQ[U : Ordering] extends MaxPQ[U]
