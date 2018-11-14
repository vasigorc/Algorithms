package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSpec
import ca.vgorcinschi.algorithms2_1.Ex2_1_24and25Spec._

class QuickSortSpec extends BaseSpec {

  trait Builder {
    val quickSort = new QuickSort[Char]
  }

  lazy private val quickSortClass: Class[QuickSort[_]] = classOf[QuickSort[_]]

  behavior of s"${quickSortClass.getSimpleName}"

  s"Basic ${quickSortClass.getSimpleName}" should s"correctly sort ${sortString}" in new Builder {
    quickSort.sort(targetArray).mkString shouldEqual sortString.sorted
  }
}