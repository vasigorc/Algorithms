package ca.vgorcinschi

import ca.vgorcinschi.algorithms2_1.BaseSort
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}

abstract class BaseSortingProps extends FlatSpec with Matchers with PropertyChecks with Gens {

  type CharSort <: BaseSort[Char]
  type IntSort <: BaseSort[Int]
  type DoubleSort <: BaseSort[Double]
  val charSort: CharSort
  val intSort: IntSort
  val doubleSort: DoubleSort

  behavior of "sort"

  it should "correctly sort random array of chars" in {
    forAll(minCharArraysGen) { a: Array[Char] =>
      charSort.sort(a) shouldEqual a.sorted
    }
  }

  it should "correctly sort random array of integers" in {
    forAll(minIntArraysGen){a: Array[Int] =>
      intSort.sort(a) shouldEqual a.sorted
    }
  }

  it should "correctly sort random array of doubles" in {
    forAll(minDoubleArraysGen){a: Array[Double] =>
      doubleSort.sort(a) shouldEqual a.sorted
    }
  }
}