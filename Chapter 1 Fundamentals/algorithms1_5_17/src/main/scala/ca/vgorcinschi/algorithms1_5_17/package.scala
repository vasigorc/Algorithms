package ca.vgorcinschi

package object algorithms1_5_17 {

  def streamOfRandom[T](generator: Generator[T]):Stream[T] = generator.generate #:: streamOfRandom(generator)
}
