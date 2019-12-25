package ca.vgorcinschi.algorithms2_3

import scala.util.Random

sealed trait Direction

object LeftDirection extends Direction

object RightDirection extends Direction

object Direction {
  def randomDirection: Direction = {
    if (Random.nextDouble() < 0.5) return LeftDirection
    RightDirection
  }
}