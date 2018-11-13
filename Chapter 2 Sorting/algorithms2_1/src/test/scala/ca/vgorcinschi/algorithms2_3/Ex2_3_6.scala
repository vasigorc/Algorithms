package ca.vgorcinschi.algorithms2_3

import org.scalameter.Bench
import org.scalameter.Measurer.MethodInvocationCount
import org.scalameter.api.{LoggingReporter, _}
import org.scalameter.execution.invocation.InvocationCountMatcher
import org.scalameter.picklers.Implicits._

import scala.util.Random

object Ex2_3_6 extends Bench.OfflineReport {

  private val NsGen: Gen[Int] = Gen.exponential("Ns")(100, 10000, 10)
  //from, until, factor
  private val testArrays: Gen[Array[Int]] = NsGen.map(n => Array.fill(n)(Random.nextInt()))

  private val quickSortInstance = new QuickSort[Int]

  override def measurer = MethodInvocationCount(
    InvocationCountMatcher.forName("ca.vgorcinschi.algorithms2_1.BaseSort", "less"))
    .map(v => v.copy(value = v.value.valuesIterator.sum))

  override val executor = SeparateJvmsExecutor(
  new Executor.Warmer.Default,
  Aggregator.min[Double],
  measurer)

  performance of "QuickSort" in {
    measure method "less" in {
      using(testArrays) in {
        sampleArray =>
          quickSortInstance.sort(sampleArray)
      }
    }
  }


  override val reporter = new LoggingReporter[Double]
  override type SameType = this.type

  override def persistor: Persistor = Persistor.None

  override def aggregator: Aggregator[Double] = Aggregator.median
}