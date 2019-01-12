package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.Gens
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class MedianOf3PartitioningProps extends FlatSpec with Matchers with GeneratorDrivenPropertyChecks with Gens {

  private val medianOf3Partitioning = new MedianOf3Partitioning[Int]

  implicit override val generatorDrivenConfig: PropertyCheckConfiguration = PropertyCheckConfig(minSuccessful = 1, maxDiscarded = 500, workers = 1)

  behavior of "sort"

  it should "sort array of ints from 0 to 100" in {
    forAll(arraysGen){  a: Array[Int] =>
      info(s"${a.mkString(",")}")
    medianOf3Partitioning.sort(a) shouldEqual a.sorted }
  }
}