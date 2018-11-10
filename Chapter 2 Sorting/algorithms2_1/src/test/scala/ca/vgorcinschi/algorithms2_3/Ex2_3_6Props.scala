package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.BaseProps
import org.scalacheck.{Arbitrary, Gen, Prop}
import org.scalameter
import org.scalameter.Measurer.MethodInvocationCount
import org.scalameter.Quantity
import org.scalameter.execution.invocation.InvocationCountMatcher

import scala.math.log

class Ex2_3_6Props extends BaseProps {

  //Ns for C_N
  private val GenNs: Gen[Int] = Gen.oneOf(100, 1000, 10000)
  private val quicksort = new QuickSort[Int]

  //arbitrary array of Ints with values between Int.MinValue and Int.MaxValue
  private val genIntArray: Gen[Array[Int]] = implicitly[Arbitrary[Array[Int]]].arbitrary

  //with length.max == n
  private def genArrayOfSize(n: Int): Gen[Array[Int]] = Gen.sized(n => genIntArray.suchThat(_.length == n))

  //mapping arrays to required lengths
  private val finalTest: Gen[Array[Int]] = for {
    n <- GenNs
    arrayGen <- genArrayOfSize(n)
  } yield arrayGen


  //tests execution
  property("Number of array indices compare should be less or equal to 2NlnN") {

    Prop.forAll(genIntArray) { x: Array[Int] =>

      val N = x.length

      val result: Quantity[Double] = scalameter.withMeasurer(MethodInvocationCount(
        InvocationCountMatcher.forName("ca.vgorcinschi.algorithms2_1.BaseSort", "less"))
        .map(v => v.copy(value = v.value.valuesIterator.sum))) measure {
        quicksort.sort(x)
      }
      info(s"${result.value}")
      result.value <= 2*N*log(N)
    }
  }
}