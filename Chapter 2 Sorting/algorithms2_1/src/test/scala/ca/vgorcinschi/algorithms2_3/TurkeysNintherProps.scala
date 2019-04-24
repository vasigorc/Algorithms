package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseSortingProps

class TurkeysNintherProps extends BaseSortingProps {

  override type CharSort = TurkeysNinther[Char]
  override type IntSort = TurkeysNinther[Int]
  override type DoubleSort = TurkeysNinther[Double]
  override val charSort: CharSort = new TurkeysNinther[Char]
  override val intSort: IntSort = new TurkeysNinther[Int]
  override val doubleSort: DoubleSort = new TurkeysNinther[Double]
}
