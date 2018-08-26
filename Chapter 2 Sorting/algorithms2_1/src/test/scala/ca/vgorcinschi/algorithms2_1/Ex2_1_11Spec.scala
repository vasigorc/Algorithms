package ca.vgorcinschi.algorithms2_1

import ca.vgorcinschi.BaseSpec

class Ex2_1_11Spec extends BaseSpec {

  import ca.vgorcinschi._

  def fixture = new {
    val customShellSort = new Ex2_1_11[Char]
  }

  "sequenceStream" should "produce a sequence 1,4,13 for N = 15" in {
    fixture.customShellSort.sequenceStream(15).toSeq should equal(Seq(1,4,13))
  }

  s"$easyShellSort" should s"be correctly sorted by ${fixture.customShellSort}" in {
    fixture.customShellSort.sort(easyShellSort) should equal(easyShellSort.sorted)
  }

  "overloaded sort" should "sort subarrays of a passed-in array" in {
    val chars = new Array[Char](21)
    "EASYSHELLSORTQUESTION".toCharArray.copyToArray(chars)

    fixture.customShellSort.sort(chars, 0, 6)

    val firstThird = new Array[Char](7)
    chars.copyToArray(firstThird, 0, 7)
    info(s"subarray: ${firstThird.toList}")
    assert(isSorted(firstThird))
  }
}
