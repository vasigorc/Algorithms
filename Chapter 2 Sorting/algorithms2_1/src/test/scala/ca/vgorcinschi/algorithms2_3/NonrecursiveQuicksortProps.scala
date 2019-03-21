package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSortingProps

class NonrecursiveQuicksortProps extends BaseSortingProps {

  override type CharSort = NonrecursiveQuicksort[Char]
  override type IntSort = NonrecursiveQuicksort[Int]
  override type DoubleSort = NonrecursiveQuicksort[Double]
  override val charSort: CharSort = new NonrecursiveQuicksort[Char]
  override val intSort: IntSort = new NonrecursiveQuicksort[Int]
  override val doubleSort: DoubleSort = new NonrecursiveQuicksort[Double]

  "sort" should "correctly sort one edge case discovered with scalacheck" in {
    doubleSort.sort(Array(1.3788925112167169E-5, 0.0)) shouldEqual Array(0.0, 1.3788925112167169E-5)
  }
}