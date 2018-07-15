package ca.vgorcinschi.algorithms_2

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.{FunSuite, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

import scala.collection.immutable.Queue
import ca.vgorcinschi._

class Ex2_2_15Props extends FunSuite with GeneratorDrivenPropertyChecks with Matchers{

  private val genIntQueue = implicitly[Arbitrary[Queue[Int]]].arbitrary
  private val genSortedIntQueue: Gen[Queue[Int]] = genIntQueue.map(_.sorted)

  test("Bottom-up mergesort should merge n queues into one"){
    forAll(genSortedIntQueue, genSortedIntQueue){
      (q1, q2)=> val result = q1.mergeQueue(q2)
        info(s"$result")
        result shouldBe sorted
    }
  }
}
