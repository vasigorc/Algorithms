package ca.vgorcinschi

import org.scalacheck.Gen
import org.scalacheck.Gen._

trait Gens {

  val arraysGen: Gen[Array[Int]] = containerOf[Array, Int](
    chooseNum(Int.MinValue, Int.MaxValue) suchThat { _ < 100 }
  ).suchThat(_.length < 50)
}