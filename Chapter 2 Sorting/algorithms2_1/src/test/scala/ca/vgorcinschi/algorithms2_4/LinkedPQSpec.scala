package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

import scala.util.Random

class LinkedPQSpec extends BaseSpec {

  trait IntPQBuilder {
    val instance = new LinkedPQ[Int] with PQMetricsCollector[Int]
  }

  behavior of "delMax"

  it should "return the highest value and decrease size by one"

  it should "throw IllegalArgumentException when PQ is empty"

  it should "guarantee logarithmic running time"

  behavior of "max"

  it should "return the highest value and leave size intact"

  it should "throw IllegalArgumentException when PQ is empty"

  behavior of "insert"

  ignore should "increase size by one" in new IntPQBuilder {
    instance.insert(42)
    instance.size() shouldEqual 1
  }

  it should "guarantee logarithmic running time" in new IntPQBuilder {
    1 to 100 foreach { _ =>
      instance.insert(Random.nextInt(1000))
    }
    math.log(instance.size()) shouldEqual instance.swimCounter
  }
}

trait PQMetricsCollector[Key] extends PQNodeSupport[Key] {

  var swimCounter: Int = 0
  var sinkCounter: Int = 0

  abstract override def sink(node: Node): Unit = {
    sinkCounter += 1
    super.sink(node)
  }

  abstract override def swim(node: Node): Node = {
    swimCounter += 1
    super.swim(node)
  }
}