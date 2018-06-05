package ca

package object vgorcinschi {

  val easyShellSort: Array[Char] = "EASYSHELLSORTQUESTION".toCharArray

  implicit class StreamOps[T](stream: Stream[T]) {
    def takeUntil(predicate: T => Boolean):Stream[T] = {
      stream.span(predicate) match {
        case (matching, missmatching) => matching #::: missmatching.take(1)
      }
    }
  }
}
