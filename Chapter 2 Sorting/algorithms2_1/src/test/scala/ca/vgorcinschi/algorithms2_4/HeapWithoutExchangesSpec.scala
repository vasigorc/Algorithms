package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

class HeapWithoutExchangesSpec extends BaseSpec {

  trait IntPQBuilder {
    val instance = new HeapWithoutExchanges[Int]
  }

  behavior of "delMax"

  it should "sink" in new IntPQBuilder {

  }

  it should "swim" in {

  }

}
