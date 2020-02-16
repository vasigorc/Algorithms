package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

trait MaxPQBehaviours {
  self: BaseSpec =>

  def nonEmptyMaxPQ[T : Ordering, ImplLà <: MaxPQ[T]](instanceSupplier: () => ImplLà, sortedInput: List[T]): Unit = {

    val reverseSorted = sortedInput.reverse
    val max = reverseSorted.head
    val inputSize = reverseSorted.size

    behavior of "size"

    it should s"be equal to ${sortedInput.size}" in {
      val instance = instanceSupplier()
      instance.size() shouldEqual sortedInput.size
    }

    behavior of "max"

    it should s"return the expected $max" in {
      val instance = instanceSupplier()
      instance.max() shouldEqual max
    }

    behavior of "delMax"

    it should "return the highest value until PQ is not empty" in {
      val instance = instanceSupplier()
      reverseSorted.foreach(n => n shouldEqual instance.delMax())
    }

    it should "decrease size by 1" in {
      val instance = instanceSupplier()
      instance.delMax()
      inputSize -1 shouldEqual instance.size()
    }

    behavior of "insert"

    it should "increase sie by 1" in {
      val instance = instanceSupplier()
      // given remove one value
      val someValue = instance.delMax()

      // when insert it back
      instance.insert(someValue)

      // then the size should be the same as initial
      inputSize shouldEqual instance.size()
    }
  }

  def emptyMaxPQ[T <: Ordered[T], ImplLà <: MaxPQ[T]](instanceSupplier: () => ImplLà, sampleT: T): Unit = {

    behavior of "size"

    it should "be equal to 0" in {
      val instance = instanceSupplier()
      instance.size() shouldEqual 0
    }

    behavior of "delMax"

    it should "throw NullPointerException when PQ is empty" in {
      val instance = instanceSupplier()
      the [NullPointerException] thrownBy instance.delMax()
    }

    behavior of "insert"

    it should "increase size by 1" in {
      val instance = instanceSupplier()
      instance.insert(sampleT)
      instance.size() shouldEqual 1
    }
  }
}