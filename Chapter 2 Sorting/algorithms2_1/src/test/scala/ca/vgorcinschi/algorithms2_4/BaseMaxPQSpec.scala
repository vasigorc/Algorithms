package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

trait BaseMaxPQSpec extends BaseSpec with MaxPQBehaviours {

  type CharMaXPQ <: MaxPQ[Char]
  type IntMaXPQ <: MaxPQ[Int]
  type DoubleMaxPQ <: MaxPQ[Double]

  def nonEmptyCharMaxPQ: CharMaXPQ
  def emptyCharMaxPQ: CharMaXPQ
  val sortedCharsList: List[Char] = List[Char]('C', 'b', 'd', 'a', 'z').sorted

  def nonEmptyIntMaxPQ: IntMaXPQ
  def emptyIntMaxPQ: IntMaXPQ
  val sortedIntList: List[Int] = List[Int](213, 3, 4, 343 ,8, 9, 100).sorted

  def nonEmptyDoubleMaxPQ: DoubleMaxPQ
  def emptyDoubleMaxPQ: DoubleMaxPQ
  val sortedDoubleList: List[Double] = List[Double](345d, 76d, 45.67, 1,23, 766d, 90.00).sorted

  behavior of "non-empty PQ"

  it should behave like nonEmptyMaxPQ(() => nonEmptyCharMaxPQ, sortedCharsList)
  it should behave like nonEmptyMaxPQ(() => nonEmptyIntMaxPQ, sortedIntList)
  it should behave like nonEmptyMaxPQ(() => nonEmptyDoubleMaxPQ, sortedDoubleList)

  behavior of "empty PQ"

  it should behave like emptyMaxPQ(() => emptyCharMaxPQ, 'A')
  it should behave like emptyMaxPQ(() => emptyIntMaxPQ, 1)
  it should behave like emptyMaxPQ(() => emptyDoubleMaxPQ, 1d)
}