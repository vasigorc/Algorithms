package ca.vgorcinschi.algorithms2_2

import ca.vgorcinschi.BaseSpec

import scala.util.Random

//basic test for k=4
class Ex2_2_25Spec extends BaseSpec{

  trait Builder {
    val instance = new Ex2_2_25[Int]
    val intArray: Array[Int] = (for (i <- 1 to 15) yield Random.nextInt(100)).toArray
  }

  behavior of "MultiwaySort"

  it should "correctly sort an array of Ints of length 15 with 4 sorts" in new Builder {
    instance.ksort(intArray, 4) shouldBe sorted
  }
}
