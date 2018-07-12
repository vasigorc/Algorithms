package ca.vgorcinschi.algorithms_2

import ca.vgorcinschi.BaseProps
import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen}

import scala.collection.immutable.Queue

class Ex2_2_14Spec extends BaseProps{

  private val genIntQueue = implicitly[Arbitrary[Queue[Int]]].arbitrary
  private val genSortedIntQueue: Gen[Queue[Int]] = genIntQueue.map(_.sorted)

  property("Merging two sorted queues should yield sorted queue"){
    import ca.vgorcinschi._
    type MyQueue = Queue[Int]
    forAll(genSortedIntQueue, genSortedIntQueue){
      case (q1:MyQueue, q2:MyQueue) => isSorted(q1.mergeQueue(q2))
    }
  }
}
