package ca.vgorcinschi.algorithms1_5_17

import org.scalacheck.{Gen, Prop}

class GeneratorProps extends BaseProps{
  val hi = 100

  property("choose"){
    val smallInteger = Gen.choose(1, hi)

    check(Prop.forAll(smallInteger){n =>
      Generator.choose(0, n).generate <= hi
    })
  }
}
