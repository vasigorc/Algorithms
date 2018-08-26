package ca.vgorcinschi.algorithms2_2

import ca.vgorcinschi.BaseSpec

class Ex2_2_16Spec extends BaseSpec{

  def sampleArray = Array(11, 1, 8, 3, 7, 0, 3, 4, 9, 8, 14)

  "sample array of integers" should "be properly sorted by Natural Bottom-Up Merge Sort" in {
    import ca.vgorcinschi._
    val naturalBtmUpSort = new Ex2_2_16[Int]()
    isSorted(naturalBtmUpSort.sort(sampleArray))
  }
}
