package ca.vgorcinschi.algorithms2_4

import scala.util.Random

class HeapWithoutExchangesSpec extends BaseMaxPQSpec {
  override type CharMaXPQ = HeapWithoutExchanges[Char]
  override type IntMaXPQ = HeapWithoutExchanges[Int]
  override type DoubleMaxPQ = HeapWithoutExchanges[Double]

  override def nonEmptyCharMaxPQ: CharMaXPQ = {
    val instance = new CharMaXPQ()
    Random.shuffle(sortedCharsList) foreach instance.insert
    instance
  }

  override def emptyCharMaxPQ: CharMaXPQ = {
    new CharMaXPQ()
  }

  override def nonEmptyIntMaxPQ: IntMaXPQ = {
    val instance = new IntMaXPQ()
    Random.shuffle(sortedIntList) foreach instance.insert
    instance
  }

  override def emptyIntMaxPQ: IntMaXPQ = {
    new IntMaXPQ()
  }

  override def nonEmptyDoubleMaxPQ: DoubleMaxPQ = {
    val instance = new DoubleMaxPQ()
    Random.shuffle(sortedDoubleList) foreach instance.insert
    instance
  }

  override def emptyDoubleMaxPQ: DoubleMaxPQ = {
    new DoubleMaxPQ()
  }
}
