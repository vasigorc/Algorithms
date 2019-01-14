package ca.vgorcinschi

import org.scalacheck.Gen
import org.scalacheck.Gen._

trait Gens {

  val minIntArraysGen: Gen[Array[Int]] = containerOf[Array, Int](Gen.chooseNum(0, 100))
}