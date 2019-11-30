package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

import scala.util.Random

class LinkedPQSpec extends BaseSpec {

  trait IntPQBuilder {
    val instance = new LinkedPQ[Int] with PQMetricsCollector[Int]
  }

  behavior of "delMax"

  it should "return the highest value" in new IntPQBuilder {
    // generate a list of random inputs
    val randomInput: List[Int] = (1 to 100).map(_ => Random.nextInt(1000)).toList

    // populate pq
    randomInput.foreach(instance.insert)

    // check that both highest value is removed every time and that the size is decreased by one
    randomInput.sorted.reverse.foreach(n => n shouldEqual instance.delMax())
  }

  it should "decrease size by one" in new IntPQBuilder {}

  it should "throw IllegalArgumentException when PQ is empty"

  it should "guarantee logarithmic running time"

  behavior of "max"

  it should "return the highest value and leave size intact"

  it should "throw IllegalArgumentException when PQ is empty"

  behavior of "insert"

  it should "increase size by one" in new IntPQBuilder {
    instance.insert(42)
    instance.size() shouldEqual 1
  }

  it should "guarantee logarithmic running time" in new IntPQBuilder {
    1 to 100 foreach { _ =>
      instance.insert(Random.nextInt(1000))
    }
    instance.swimCounter should be <= 2 * math.log(instance.size()).toInt
  }
}

/**
  * Attention! This hook is not designed to be thread-safe if the same
  * [[LinkedPQ]] object is accessed by multiple threads
  * @tparam Key
  */
trait PQMetricsCollector[Key] extends LinkedPQ[Key] {

  var swimCounter: Int = 0
  var sinkCounter: Int = 0

  // insert part
  override def insert(value: Key): Unit = {
    // reset counter on every call
    swimCounter = 0
    super.insert(value)
  }

  override protected def insertHelper(maybeNextNode: Option[Node], maybeParentNode: Option[Node], value: Key): Node = {
    swimCounter += 1
    super.insertHelper(maybeNextNode, maybeParentNode, value)
  }

  override def swim(node: Node): Node = {
    swimCounter += 1
    super.swim(node)
  }

  // delMax part
  override def delMax(): Key = {
    // reset counter on every call
    sinkCounter = 0
    super.delMax()
  }

  override def sink(node: Node): Node = {
    sinkCounter += 1
    super.sink(node)
  }
}