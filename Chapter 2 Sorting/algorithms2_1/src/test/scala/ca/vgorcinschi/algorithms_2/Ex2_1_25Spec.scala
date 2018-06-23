package ca.vgorcinschi.algorithms_2

import ca.vgorcinschi.BaseSpec

class Ex2_1_25Spec extends BaseSpec{

  val sortString = "EASYQUESTION"
  private val insertionSort = new InsertionSort[Char]

  "Basic Insertion Sort" should "correctly sort \"EasyQuestion\"" in {
    val targetArray = sortString.toCharArray
    val resultingArray = insertionSort.sort(targetArray)
    resultingArray.mkString shouldEqual sortString.sorted
  }
}
