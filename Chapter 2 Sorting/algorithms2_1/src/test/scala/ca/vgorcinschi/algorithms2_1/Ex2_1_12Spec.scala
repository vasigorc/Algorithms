package ca.vgorcinschi.algorithms2_1

import ca.vgorcinschi.{BaseSpec, easyShellSort}

class Ex2_1_12Spec extends BaseSpec{

  val someSmallConstant = 0.6

  def fixture = new {
    val customShellSort = new Ex2_1_12[Char]
  }

  s"$easyShellSort" should s"be correctly sorted by ${fixture.customShellSort}" in {
    fixture.customShellSort.sort(easyShellSort) should equal(easyShellSort.sorted)
  }


  /* Write a test client that tests the hypothesis that this number is a small constant, by sorting arrays of random Double
  * values using array sizes that are increasing powers of 10, starting at 100.*/
  "for an increasing (by power of 10) array of Doubles shell sort's average nr of compares per gap" should "be a small " +
    "constant" in {
    //FIXME test gets OOMEs
    assert(Stream.iterate(100D)(i => scala.math.pow(i, 2)).take(3).forall{
      d => {
        val shellSort = new Ex2_1_12[Double]
        val doubles = Array.fill(d.toInt){math.random}
        shellSort.sort(doubles)
        (shellSort.allRatios.sum / shellSort.allRatios.length) < someSmallConstant
      }
    })
  }
}
