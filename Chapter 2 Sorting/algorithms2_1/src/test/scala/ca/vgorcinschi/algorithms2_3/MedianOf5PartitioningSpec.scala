package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSpec

class MedianOf5PartitioningSpec extends BaseSpec {

  private val EASY_QUICKSORT = "EASYQUICKSORTQUESTION"
  private val testCase: Array[Char] = EASY_QUICKSORT toCharArray

  trait IntMedianBuilder {
    val medianOf5Partitioning = new MedianOf3Partitioning[Int]
  }

  trait CharMedianBuilder {
    val medianOf5Partitioning = new MedianOf3Partitioning[Char]
  }
  behavior of "MedianOf5PartitioningSpec"

  it should "sort" in new CharMedianBuilder {
    private val actualSortedArray: Array[Char] = medianOf5Partitioning sort testCase
    actualSortedArray shouldEqual testCase.sorted
  }

}
