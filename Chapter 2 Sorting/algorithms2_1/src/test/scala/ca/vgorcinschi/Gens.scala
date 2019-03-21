package ca.vgorcinschi

import org.scalacheck.Gen
import org.scalacheck.Gen._

trait Gens {

  val minIntArraysGen: Gen[Array[Int]] = containerOf[Array, Int](Gen.chooseNum(0, 100))
  val minDoubleArraysGen: Gen[Array[Double]] = containerOf[Array, Double](Gen.chooseNum(0.00, 100.00))
  val minCharArraysGen: Gen[Array[Char]] = containerOf[Array, Char](Gen.alphaLowerChar)
}