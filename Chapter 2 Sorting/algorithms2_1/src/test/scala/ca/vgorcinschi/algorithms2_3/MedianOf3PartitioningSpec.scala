package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSpec

class MedianOf3PartitioningSpec extends BaseSpec {

  private val EASY_QUICKSORT = "EASYQUICKSORTQUESTION"
  private val testCase: Array[Char] = EASY_QUICKSORT toCharArray

  behavior of "MedianOf3PartitioningSpec"

  it should s"sort $EASY_QUICKSORT" in {
    val medianOf3Partitioning = new MedianOf3Partitioning[Char]
    val maybeSortedArray = medianOf3Partitioning sort testCase
    maybeSortedArray shouldEqual testCase.sorted
  }

  it should s"sort an empty array" in {
    val medianOf3Partitioning = new MedianOf3Partitioning[Int]
    val emptyArray = medianOf3Partitioning sort Array[Int]()
    emptyArray shouldEqual Array[Int]()
  }

  it should "sort an array of length equal to 3" in {
    val medianOf3Partitioning = new MedianOf3Partitioning[Int]
    val intsArray = medianOf3Partitioning sort Array(3, 1, 2)
    intsArray shouldEqual Array(1, 2, 3)
  }
}