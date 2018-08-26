package ca.vgorcinschi.algorithms2_2

import ca.vgorcinschi.{BaseSpec, _}

class Ex2_2_12Spec extends BaseSpec{

  "greatest common divisor for 25" should "be 5" in {
    descedingValidDivisors(25).take(1).seq(0) shouldEqual(5)
  }

  "an array" should "at least be sorted" in {
    val test = "MERGEWITHSHELLSORTEXAMPLE".toCharArray
    val customMergeSort = new Ex2_2_12[Char]
    customMergeSort.sort(test)
    info(s"target array after sorting is ${test.toList}")
    assert(isSorted(test))
  }
}
