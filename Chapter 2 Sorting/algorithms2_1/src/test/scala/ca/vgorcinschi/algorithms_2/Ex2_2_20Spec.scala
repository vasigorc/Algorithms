package ca.vgorcinschi.algorithms_2

import ca.vgorcinschi.BaseSpec

class Ex2_2_20Spec extends BaseSpec{

  trait Builder {
    val instance = new Ex2_2_20[Char]
  }

  behavior of "IndexSort"

  it should "return corresponding indexes of a sample array after mergesort" in new Builder{
    val sampleArray = Array('Z', 'C', 'D', 'Y', 'A', 'M', 'B')
    private val indicesArray: Array[Int] = instance.indexSort(sampleArray)
    indicesArray shouldEqual Array(4, 6, 1, 2, 5, 3, 0)
  }
}
