package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

trait MaxPQBehaviours[ImplLà[_] <: MaxPQ[_]] {
  self: BaseSpec =>

  def delMax[Val](newMaxPQ: => ImplLà[Val], maxInt: Val)(implicit ev: Val <:< Ordered[Val]): Unit = {

    behavior of "delMax"

    it should s"return the expected $maxInt" in {
      newMaxPQ.delMax() shouldEqual  maxInt
    }
  }
}
