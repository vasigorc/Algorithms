package ca.vgorcinschi.algorithms1_4_34.hotandcold

trait HotAndCold {
  protected val secret:Int
  protected def guess(guess: Int)
  def play
}