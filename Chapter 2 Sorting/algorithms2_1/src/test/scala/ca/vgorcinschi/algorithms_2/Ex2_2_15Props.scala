package ca.vgorcinschi.algorithms_2

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

class Ex2_2_15Props extends FunSuite with GeneratorDrivenPropertyChecks with Matchers{

  lazy val codeUnderTest = new Ex2_2_15[Int]

  test("Bottom-up mergesort should merge n queues into one"){
    forAll {(arr: Array[Int])=>
      val queue = codeUnderTest.sort(arr)
      info(s"$queue")
      queue shouldBe sorted
    }
  }
}
