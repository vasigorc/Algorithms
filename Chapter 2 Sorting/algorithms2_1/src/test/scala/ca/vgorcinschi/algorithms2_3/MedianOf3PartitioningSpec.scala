package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSpec

class MedianOf3PartitioningSpec extends BaseSpec {

  private val testCase: Array[Char] = "EASYQUICKSORTQUESTION" toCharArray

  behavior of "MedianOf3PartitioningSpec"

  it should "sort" in {
    val medianOf3Partitioning = new MedianOf3Partitioning[Char]
    val maybeSortedArray = medianOf3Partitioning sort(testCase)
    maybeSortedArray shouldBe sorted
  }

}
