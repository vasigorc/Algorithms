package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSortingProps

class Fast3WayPartitioningProps extends BaseSortingProps {

  override type CharSort = Fast3WayPartitioning[Char]
  override type IntSort = Fast3WayPartitioning[Int]
  override type DoubleSort = Fast3WayPartitioning[Double]
  override val charSort: CharSort = new Fast3WayPartitioning[Char]
  override val intSort: IntSort = new Fast3WayPartitioning[Int]
  override val doubleSort: DoubleSort = new Fast3WayPartitioning[Double]

}
