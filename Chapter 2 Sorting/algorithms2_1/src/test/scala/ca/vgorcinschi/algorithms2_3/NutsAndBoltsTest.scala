package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSpec

import scala.util.Random

class NutsAndBoltsTest extends BaseSpec {

  trait CharBased {
    private val nuts = Vector[Char]('#', '$', '^', '&', '*')
    private val bolts = Random.shuffle(nuts)
    val instance = NutsAndBolts[Char](nuts, bolts)
  }

  trait IntBased {
    private val nuts = Vector.fill(10)(Random.nextInt(100))
    private val bolts = Random.shuffle(nuts)
    val instance = NutsAndBolts[Int](nuts, bolts)
  }

  behavior of "NutsAndBoltsTest"

  ignore should "match chars" in new CharBased {
    instance sort() foreach { case (nut, bolt) => nut shouldEqual bolt }
  }


  it should "match ints" in new IntBased {
    instance sort() foreach {case (nut, bolt) => nut shouldEqual bolt}
  }
}
