package ca.vgorcinschi.algorithms2_4.immutable

import scala.language.higherKinds

/**
 * Exercise 2.4.27 Find the minimum
 * Add a min() method to MaxPQ. Your implementation
 * should use constant time and constant extra space
 *
 * @tparam U
 */
trait FindMin[U] {

  /**
   * whoever using this mixin must also extend [[ca.vgorcinschi.algorithms2_4.immutable.AbstractMaxPQ]]
   * so that the implementation class expresses implementation
   * code for both in one place
   */
  self: AbstractMaxPQ[U] =>

  /**
   * Doesn't remove anything from the
   * PQ
   * @return the smallest element if present
   */
  def min(): Option[U]
}
