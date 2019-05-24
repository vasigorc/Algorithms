package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSortingProps

class SampleSortProps extends BaseSortingProps {

  override type CharSort = SampleSort[Char]
  override type IntSort = SampleSort[Int]
  override type DoubleSort = SampleSort[Double]
  override val charSort = new SampleSort[Char](10)
  override val intSort = new SampleSort[Int](10)
  override val doubleSort = new SampleSort[Double](10)
}