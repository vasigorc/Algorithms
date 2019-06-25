package ca.vgorcinschi.algorithms2_4

import ca.vgorcinschi.BaseSpec

import scala.util.Random

class ResizableMaxPQSpec extends BaseSpec {

  trait IntPQBuilder {
    val instance = new ResizableMaxPQ[Int]

    def add(n: Int): Unit = 0 until n foreach { _ =>
      instance insert (Random nextInt 100)
    }

    def remove(n: Int): Seq[Int] = (0 until n).map(_ => instance.delMax())
  }

  behavior of "ResizableMaxPQSpec"

  it should "resizable on insert" in new IntPQBuilder {
    add(100)
    instance.size() shouldEqual(100)
  }

  it should "resizable on insert and then delMax" in new IntPQBuilder {
    add(100)
    remove(100)
    instance.size() shouldEqual(0)
  }

  "delMax" should "always dequeue the largest value" in new IntPQBuilder {
    add(100)
    private val ints: Seq[Int] = remove(100)
    ints.reverse shouldBe sorted
  }
}
