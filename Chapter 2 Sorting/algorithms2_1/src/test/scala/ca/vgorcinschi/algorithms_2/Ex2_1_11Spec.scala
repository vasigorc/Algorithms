package ca.vgorcinschi.algorithms_2

import ca.vgorcinschi.{BaseSpec, easyShellSort}

class Ex2_1_11Spec extends BaseSpec {

  def fixture = new {
    val customShellSort = new Ex2_1_11[Char]
  }

  "sequenceStream" should "produce a sequence 1,4,13 for N = 15" in {
    fixture.customShellSort.sequenceStream(15).toSeq should equal(Seq(1,4,13))
  }

  s"$easyShellSort" should s"be correctly sorted by ${fixture.customShellSort}" in {
    fixture.customShellSort.sort(easyShellSort) should equal(easyShellSort.sorted)
  }
}
