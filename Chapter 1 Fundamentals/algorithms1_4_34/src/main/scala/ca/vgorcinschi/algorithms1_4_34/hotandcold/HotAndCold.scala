package ca.vgorcinschi.algorithms1_4_34.hotandcold

trait HotAndCold {
  protected val secret:Int
  protected def guess(attempt: Int):Unit
  def play
  protected var counter:Int = _
  protected var previousIndex:Int = _
}