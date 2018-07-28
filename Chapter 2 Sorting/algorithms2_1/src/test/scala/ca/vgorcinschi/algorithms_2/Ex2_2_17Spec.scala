package ca.vgorcinschi.algorithms_2

import ca.vgorcinschi.BaseSpec

class Ex2_2_17Spec extends BaseSpec {

  def sampleList = List(11, 1, 8, 3, 7, 0, 3, 4, 9, 8, 14)

  behavior of "Bottom-Up merge sort on a list"

  it should "sort a list of Ints" in {
    import ca.vgorcinschi._

    val result = new Ex2_2_17[Int].mergesort(sampleList)
    result shouldBe sorted
  }
}
