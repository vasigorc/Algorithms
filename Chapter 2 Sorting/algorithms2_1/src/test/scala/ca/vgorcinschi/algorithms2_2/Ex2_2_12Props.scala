package ca.vgorcinschi.algorithms2_2

import ca.vgorcinschi.{BaseProps, _}
import org.scalacheck.Gen
import org.scalacheck.Prop._

class Ex2_2_12Props extends BaseProps{

  lazy val intPairs = for {
    left <- Gen.chooseNum(1, 1000)
    right <- Gen.chooseNum(1, 1000)
  } yield (left, right)

  property("implementation of gcd that switches variables in a while loop"){
    forAll(intPairs){case (left: Int, right: Int) =>
      gcdOldWay(left, right) == gcd(left, right)
    }
  }

  property("descedingValidDivisors"){
    forAll { n: Int =>
      (n >= 0)
      descedingValidDivisors(n) == gcdOldWay(n, 1)
    }
  }
}
