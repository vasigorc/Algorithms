package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BasePropertyChecks
import org.scalacheck.Gen
import org.scalameter
import org.scalameter.Measurer.MethodInvocationCount
import org.scalameter.Quantity
import org.scalameter.execution.invocation.InvocationCountMatcher

import scala.math.log

class Ex2_3_6Props extends BasePropertyChecks {

  //Ns for C_N
  private val GenNs: Gen[Int] = Gen.oneOf(100, 1000, 10000)
  private val quicksort = new QuickSort[Int]

  //mapping arrays to required lengths
  private val finalTest: Gen[Array[Int]] = Gen.containerOf[Array, Int](GenNs)

  //tests execution
  test("Number of array indices compare should be less or equal to 2NlnN") {

    forAll(finalTest) { x: Array[Int] =>

      whenever(x.nonEmpty) {

        val N = x.length

        info(s"array size is: $N")

        val result: Quantity[Double] = scalameter.withMeasurer(MethodInvocationCount(
          InvocationCountMatcher.forName("ca.vgorcinschi.algorithms2_1.BaseSort", "less"))
          .map(v => v.copy(value = v.value.valuesIterator.sum))) measure {
          quicksort.sort(x)
        }
        info(s"less was called ${result.value} times")
        result.value <= 2 * N * log(N)
      }

    }
  }
}