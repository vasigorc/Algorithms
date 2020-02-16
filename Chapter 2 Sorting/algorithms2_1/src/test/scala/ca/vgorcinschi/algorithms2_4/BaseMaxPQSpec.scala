package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

trait BaseMaxPQSpec extends BaseSpec with MaxPQBehaviours {

  type CharMaXPQ <: MaxPQ[Char]
  type IntMaXPQ <: MaxPQ[Int]
  type DoubleMaxPQ <: MaxPQ[Double]

  def charMaxPQ: CharMaXPQ
  val sortedCharsList: List[Char] = List[Char]('C', 'b', 'd', 'a', 'z').sorted

  def intMaxPQ: IntMaXPQ

  def doubleMaxPQ: DoubleMaxPQ

  it should behave like nonEmptyMaxPQ(() => charMaxPQ, sortedCharsList)
}