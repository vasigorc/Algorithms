package ca.vgorcinschi.algorithms2_3

import org.scalatest.MustMatchers._
import org.scalatest.WordSpec
import org.scalatest.prop.PropertyChecks

class MedianOf3PartitioningProps extends WordSpec with PropertyChecks {

  "sort method" must {
    "sort any Int array" in {
      forAll { (l: List[Int]) =>
        whenever(l.nonEmpty) {
          val intArray: Array[Int] = l.toArray
          val maybeSorted = new MedianOf3Partitioning[Int].sort(l.toArray)
          maybeSorted must equal (intArray.sorted)
        }
      }
    }
  }
}