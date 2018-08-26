package ca.vgorcinschi.algorithms2_1

import ca.vgorcinschi.BaseSpec

class Ex2_1_24and25Spec extends BaseSpec{

  val sortString = "EASYQUESTION"
  private val insertionSort = new InsertionSort[Char]
  private val optimizedIS = new Ex2_1_25[Char]
  private val sentinelIS = new SentinelInsertionSort[Char]
  def targetArray: Array[Char] = sortString.toCharArray
  def resultingArray(alg: BaseSort[Char]): Array[Char] = alg.sort(targetArray)

  "Basic Insertion Sort" should "correctly sort \"EasyQuestion\"" in {
    resultingArray(insertionSort).mkString shouldEqual sortString.sorted
  }

  "Optimized Insertion Sort" should "correctly sort \"EasyQuestion\"" in {
    resultingArray(optimizedIS).mkString shouldEqual sortString.sorted
  }

  "Sentinel Insertion Sort" should "correctly sort \"EasyQuestion\"" in {
    resultingArray(sentinelIS).mkString shouldEqual sortString.sorted
  }
}
