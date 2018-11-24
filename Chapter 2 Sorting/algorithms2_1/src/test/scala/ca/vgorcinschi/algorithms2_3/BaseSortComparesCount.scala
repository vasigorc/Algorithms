package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.MethodInvocationCountTemplate
import ca.vgorcinschi.algorithms2_1.BaseSort
import org.scalameter.api.Gen

import scala.util.Random

trait BaseSortComparesCount extends {
  //pre-initialized fields are initialized before the superclass constructor is called
  val classUnderTest = classOf[BaseSort[_]].getName
  val methodName = "less"

} with MethodInvocationCountTemplate {
  //test samples
  protected val NsGen: Gen[Int] = Gen.exponential("Ns")(100, 10000, 10)
  protected val quickSortInstance = new QuickSort[Int]
  protected val testArrays: Gen[Array[Int]] = NsGen.map(n => Array.fill(n)(Random.nextInt()))
}