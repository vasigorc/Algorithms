package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

import scala.language.higherKinds

trait MaxPQBehaviours[ImplLà[_] <: MaxPQ[_]] {
  self: BaseSpec =>

  def nonEmptyMaxPQ[Val](instance: => ImplLà[Val], sortedInput: List[Val])(implicit ev: Val <:< Ordered[Val]): Unit = {

    val max = sortedInput.head

    behavior of "size"

    it should s"be equal to ${sortedInput.size}" in {
      instance.size() shouldEqual sortedInput.size
    }

    behavior of "max"

    it should s"return the expected $max" in {
      instance.max() shouldEqual  max
    }

    behavior of "delMax"

    it should "return the highest value until PQ is not empty" in {
      sortedInput.reverse.foreach(n => n shouldEqual instance.delMax())
    }

  }
}