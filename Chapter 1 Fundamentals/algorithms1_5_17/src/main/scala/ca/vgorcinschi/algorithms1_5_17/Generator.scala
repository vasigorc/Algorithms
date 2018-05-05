package ca.vgorcinschi.algorithms1_5_17

object Generator {

  val integers = new Generator[Int] {

    val rand = new java.util.Random

    override def generate: Int = rand.nextInt()
  }

  def choose(lo: Int = 0, hi: Int):Generator[Int] =
    for (x <- integers) yield lo + x % (hi-lo)

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