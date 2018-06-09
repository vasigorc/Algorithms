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

  def gcd(a: Int, b: Int): Int = if(b==0) a else gcd(b, a % b)

  // From Stepanov's and Rose's "From Mathematics to Generic Programming"
  def gcdOldWay(a: Int, b: Int):Int = {
    var x =a
    var y =b
    while(y != 0){
      x = x % y
      //swap variables' values
      x = x+y
      y = x-y
      x = x-y
    }
    x
  }
}
