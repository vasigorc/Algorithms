package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSpec

import scala.util.Random

class NutsAndBoltsTest extends BaseSpec {

  trait TestBuilder {
    private val nuts = Vector[Char]('#', '$', '^', '&', '*')
    private val bolts = Random.shuffle(nuts)
    val instance = NutsAndBolts[Char](nuts, bolts)
  }

  behavior of "NutsAndBoltsTest"

  it should "sort" in new TestBuilder {
    instance.sort().foreach { case (nut, bolt) => nut shouldEqual bolt }
  }

}
