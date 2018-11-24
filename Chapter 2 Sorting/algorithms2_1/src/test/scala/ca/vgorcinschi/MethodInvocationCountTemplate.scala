package ca.vgorcinschi

import org.scalameter.Bench
import org.scalameter.Measurer.MethodInvocationCount
import org.scalameter.api._
import org.scalameter.execution.invocation.InvocationCountMatcher
import org.scalameter.picklers.Implicits._

trait MethodInvocationCountTemplate extends Bench.OfflineReport {

  val classUnderTest: String
  val methodName: String

  override def measurer = MethodInvocationCount(
    InvocationCountMatcher.forName(classUnderTest, methodName))
    .map(v => v.copy(value = v.value.valuesIterator.sum))

  override val executor = SeparateJvmsExecutor(
    new Executor.Warmer.Default,
    Aggregator.min[Double],
    measurer)

  override val reporter = new LoggingReporter[Double]

  override def persistor: Persistor = Persistor.None
}