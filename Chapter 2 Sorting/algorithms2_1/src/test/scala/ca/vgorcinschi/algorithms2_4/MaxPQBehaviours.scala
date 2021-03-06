package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

import scala.reflect.ClassTag

trait MaxPQBehaviours {
  self: BaseSpec =>

  def nonEmptyMaxPQ[T : Ordering, ImplLà <: MaxPQ[T]](instanceSupplier: () => ImplLà, sortedInput: List[T])
                                                     (implicit ctag: ClassTag[T]): Unit = {

    val className = ctag.runtimeClass.asInstanceOf[Class[T]].getSimpleName
    val reverseSorted = sortedInput.reverse
    val max = reverseSorted.head
    val inputSize = reverseSorted.size

    behavior of s"size for $className"

    it should s"be equal to ${sortedInput.size}" in {
      val instance = instanceSupplier()
      instance.size() shouldEqual sortedInput.size
    }

    behavior of s"max for $className"

    it should s"return the expected $max" in {
      val instance = instanceSupplier()
      instance.max() shouldEqual max
    }

    behavior of s"delMax for $className"

    it should "return the highest value until PQ is not empty" in {
      val instance = instanceSupplier()
      reverseSorted.foreach { n =>
        val currentMax = instance.delMax()
        n shouldEqual currentMax
      }
    }

    it should "decrease size by 1" in {
      val instance = instanceSupplier()
      instance.delMax()
      inputSize -1 shouldEqual instance.size()
    }

    behavior of s"insert for $className"

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

  def emptyMaxPQ[T : Ordering, ImplLà <: MaxPQ[T]](instanceSupplier: () => ImplLà, sampleT: T)
                                                  (implicit ctag: ClassTag[T]): Unit = {

    val className = ctag.runtimeClass.asInstanceOf[Class[T]].getSimpleName

    behavior of s"size for $className"

    it should "be equal to 0" in {
      val instance = instanceSupplier()
      instance.size() shouldEqual 0
    }

    behavior of s"delMax for $className"

    it should "throw NoSuchElementException when PQ is empty" in {
      val instance = instanceSupplier()
      the [NoSuchElementException] thrownBy instance.delMax()
    }

    behavior of s"insert for $className"

    it should "increase size by 1" in {
      val instance = instanceSupplier()
      instance.insert(sampleT)
      instance.size() shouldEqual 1
    }
  }
}