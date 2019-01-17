package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSpec

class MedianOf3PartitioningSpec extends BaseSpec {

  private val EASY_QUICKSORT = "EASYQUICKSORTQUESTION"
  private val testCase: Array[Char] = EASY_QUICKSORT toCharArray

  trait IntMedianBuilder {
    val medianOf3Partitioning = new MedianOf3Partitioning[Int]
  }

  behavior of "MedianOf3PartitioningSpec"

  it should s"sort $EASY_QUICKSORT" in {
    val medianOf3Partitioning = new MedianOf3Partitioning[Char]
    val maybeSortedArray = medianOf3Partitioning sort testCase
    maybeSortedArray shouldEqual testCase.sorted
  }

  it should s"sort an empty array" in new IntMedianBuilder {
    val emptyArray = medianOf3Partitioning sort Array[Int]()
    emptyArray shouldEqual Array[Int]()
  }

  it should "sort an array of length equal to 3" in new IntMedianBuilder {
    val intsArray = medianOf3Partitioning sort Array(3, 1, 2)
    intsArray shouldEqual Array(1, 2, 3)
  }

  it should "sort an array of length 2" in new IntMedianBuilder {
    private val intsArray: Array[Int] = medianOf3Partitioning sort Array(0, -1)
    intsArray shouldEqual Array(-1, 0)
  }

  behavior of "failing property tests"

  it should "sort an array of seven items" in new IntMedianBuilder {
    private val intsArray: Array[Int] = medianOf3Partitioning sort Array(1, 1, 100, 20, 52, 1, 5)
    intsArray shouldEqual intsArray.sorted
  }
}