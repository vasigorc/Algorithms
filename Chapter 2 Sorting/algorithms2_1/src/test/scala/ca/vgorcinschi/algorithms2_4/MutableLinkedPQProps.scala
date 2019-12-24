package ca.vgorcinschi.algorithms2_4

import org.scalatest.prop.{GeneratorDrivenPropertyChecks, PropertyChecks}
import org.scalatest.{Matchers, WordSpec}

class MutableLinkedPQProps extends WordSpec with PropertyChecks with GeneratorDrivenPropertyChecks with Matchers {

  trait IntPQBuilder {
    val instance = new MutableLinkedPQ[Int]() with PQMetricsCollector[Int]
  }

  "Linked PQ insert" should {
    "guarantee O(log(N)) running time" in new IntPQBuilder {
      forAll {elem: Int =>
        instance.insert(elem)

        val size = instance.size()
        val logFactor = math.max(2, size / 2)

        val expected = logFactor * math.max(1, math.log(size)).toInt
        val actual = instance.insertStepsCounter
        actual should be <= expected
      }
    }
  }
}