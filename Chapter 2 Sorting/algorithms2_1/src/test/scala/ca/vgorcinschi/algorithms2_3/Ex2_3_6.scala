package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort
import org.scalameter.{Bench, Parameters}
import org.scalameter.Measurer.MethodInvocationCount
import org.scalameter.api.{LoggingReporter, _}
import org.scalameter.execution.invocation.InvocationCountMatcher
import org.scalameter.picklers.Implicits._
import sun.misc.VM

import scala.util.Random

object Ex2_3_6 extends Bench.OfflineReport {

  private val NsGen: Gen[Int] = Gen.exponential("Ns")(100, 10000, 10)
  private val quickSortInstance = new QuickSort[Int]

  private val testArrays: Gen[Array[Int]] = NsGen.map(n => Array.fill(n)(Random.nextInt()))

  override def measurer = MethodInvocationCount(
    InvocationCountMatcher.forName(classOf[BaseSort[_]].getName, "less"))
    .map(v => v.copy(value = v.value.valuesIterator.sum))

  override val executor = SeparateJvmsExecutor(
  new Executor.Warmer.Default,
  Aggregator.min[Double],
  measurer)

  performance of quickSortInstance.getClass.getSimpleName in {
    measure method "less" in {
      using(testArrays) in {
        sampleArray =>
          quickSortInstance.sort(sampleArray)
      }
    }
  }

//  ::Benchmark QuickSort.less::
//    cores: 8
//  hostname: localhost.localdomain
//  name: Java HotSpot(TM) 64-Bit Server VM
//  osArch: amd64
//  osName: Linux
//  vendor: Oracle Corporation
//    version: 25.161-b12
//  Parameters(Ns -> 100): 680.0 #  2NlnN = 921
//  Parameters(Ns -> 1000): 11611.0 #  2NlnN = 13815
//  Parameters(Ns -> 10000): 164166.0 # 2NlnN = 184206

  override val reporter = new LoggingReporter[Double]

  override def persistor: Persistor = Persistor.None
}