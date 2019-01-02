package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSpec
import ca.vgorcinschi.algorithms2_1.Ex2_1_24and25Spec._

class QuickSortSpec extends BaseSpec {

  trait QuickSortBuilder {
    val quickSort = new QuickSort[Char]
  }

  trait SentinelsQuickSortBuilder {
    val sentinelsQuickSort = new SentinelsQuickSort[Char]
  }

  lazy private val quickSortClass: Class[QuickSort[_]] = classOf[QuickSort[_]]
  lazy private val sentinelsQuickSortClass: Class[SentinelsQuickSort[_]] = classOf[SentinelsQuickSort[_]]

  behavior of s"${quickSortClass.getSimpleName}"

  s"Basic ${quickSortClass.getSimpleName}" should s"correctly sort ${sortString}" in new QuickSortBuilder {
    quickSort.sort(targetArray).mkString shouldEqual sortString.sorted
  }

  behavior of s"${sentinelsQuickSortClass.getSimpleName}"

  s"Basic ${sentinelsQuickSortClass.getSimpleName}" should s"correctly sort $sortString" in new SentinelsQuickSortBuilder {
    sentinelsQuickSort.sort(targetArray).mkString shouldEqual sortString.sorted
  }
}