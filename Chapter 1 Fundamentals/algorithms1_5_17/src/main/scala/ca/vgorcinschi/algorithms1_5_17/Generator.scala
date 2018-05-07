package ca.vgorcinschi.algorithms1_5_17

object Generator {

  val integers = new Generator[Int] {

    val rand = new java.util.Random

    override def generate: Int = rand.nextInt()
  }

  def choose(lo: Int, hi: Int):Generator[Int] ={
    val diff = hi - lo
    for (x <- integers) yield diff match {
      case 0 => 0
      case _ => lo + x % diff
    }
  }

  def positiveInts(hi: Int = Int.MaxValue):Generator[Int] = {
    require(hi > 0)
    for (x <- choose(0, hi)) yield Math.abs(hi)
  }

  def pairs[T, U](t: Generator[T], u: Generator[U]) = new Generator[(T, U)] {
    def generate = (t.generate, u.generate)
  }
}

trait Generator[+T]{
  self =>    //an alias for this

  def generate: T

  def map[S](f: T => S):Generator[S] = new Generator[S] {
    override def generate = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]):Generator[S] = new Generator[S] {
    override def generate = f(self.generate).generate
  }
}