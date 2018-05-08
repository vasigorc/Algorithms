package ca.vgorcinschi.algorithms1_5_17

import org.scalacheck.{Gen, Prop}
import org.scalatest.prop.Checkers
import org.scalatest.{Matchers, PropSpec}

class GeneratorProps extends PropSpec with Checkers with Matchers{
  val hi = 100

  property("choose"){
    val smallInteger = Gen.choose(1, hi)

    check(Prop.forAll(smallInteger){n =>
      Generator.choose(0, n).generate <= hi
    })
  }
}