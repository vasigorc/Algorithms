package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.Gens
import org.scalatest.MustMatchers._
import org.scalatest.WordSpec
import org.scalatest.prop.PropertyChecks

class MedianOf3PartitioningProps extends WordSpec with PropertyChecks with Gens {

  "sort method" must {
    "sort any Int array" in {
      forAll (minIntArraysGen){ (a: Array[Int]) =>
        whenever(a.nonEmpty) {
          val maybeSorted = new MedianOf3Partitioning[Int].sort(a)
          maybeSorted must equal (a.sorted)
        }
      }
    }
  }
}