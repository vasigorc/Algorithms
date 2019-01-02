package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BasePropertyChecks

class SentinelsQuickSortTest extends BasePropertyChecks {

  lazy private val instance = new SentinelsQuickSort[Int]
  lazy private val sentinelsQuickSortClass = classOf[SentinelsQuickSort[Int]].getSimpleName

  test(s"$sentinelsQuickSortClass should correctly sort any Int array") {
    forAll {(array: Array[Int]) =>
      val ints = instance.sort(array)
      ints shouldBe sorted
    }
  }

}
