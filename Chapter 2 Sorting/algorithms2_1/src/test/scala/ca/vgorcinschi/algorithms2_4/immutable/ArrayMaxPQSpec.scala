package ca.vgorcinschi.algorithms2_4.immutable

import ca.vgorcinschi.BaseSpec
import cats.Eval

import scala.util.Random

class ArrayMaxPQSpec extends BaseSpec {

  behavior of "min"

  trait EmptyIntArrayMaxPQ {
    val instance = new ArrayMaxPQ[Int]()
  }

  trait NonEmptyIntArrayMaxPQ extends RandomIntsArray {
    val instance: ArrayMaxPQ[Int] = randomInts.foldLeft(new ArrayMaxPQ[Int]())((pq, int) => pq.insert(int))
  }

  trait RandomIntsArray {
    private val intProvider = Eval.always(Random.nextInt(100))
    val randomInts: List[Int] = List.fill(10)(intProvider.value)
  }

  it should "return a None from an empty MaxPQ" in new EmptyIntArrayMaxPQ {
    instance.min() shouldEqual None
  }

  it should "return a Some of minimal value in MaxPQ" in new EmptyIntArrayMaxPQ {
    val nonEmptyPQ: ArrayMaxPQ[Int] = List(1, 2, 3, 4).foldLeft(instance)((pq, next) => pq.insert(next))
    nonEmptyPQ.min() shouldEqual Some(1)
  }

  behavior of "insert"

  it should
    """create a new MaxPQ on each operation
    and the size of each subsequent instance should
    be +1 relative to the preceding one""" in new EmptyIntArrayMaxPQ with RandomIntsArray {

    private val maxPQs: List[Int] = randomInts
      .foldLeft(List(instance))((acc, int) => acc.head.insert(int)::acc)
      .map(_.size)

    maxPQs.sliding(size = 2).foreach {
      case ::(head, tl) => head should be > tl.head
    }
  }

  behavior of "delMax"

  /**
   * [[Iterable.toList]] relies on the [[Iterator]]
   */
  it should "continually return the greatest value from the encapsulated array" in
    new NonEmptyIntArrayMaxPQ {

      instance.toList.reverse shouldBe sorted

  }
}
