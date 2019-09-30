package ca.vgorcinschi.algorithms2_3

import scala.util.Random

sealed trait Direction

object Left extends Direction

object Right extends Direction

object Direction {
  def randomDirection: Direction = {
    if (Random.nextDouble() < 0.5) return Left
    Right
  }
}