package ca.vgorcinschi.algorithms1_4_34.hotandcold

import ca.vgorcinschi.algorithms1_4_34._

class HotAndColdImpl(upperBound: Int) extends {
  val secret = scala.util.Random.nextInt(upperBound-1)
} with HotAndCold {
  
  private val array = (0 to upperBound).toArray
  private lazy val secretIndex = binary(array, secret)  
  /*
   * the main logic of the application goes into this method
   */
  override def guess(guess: Int) = {
    ???
  }
  
  override def play = println("Secret's index in the array is: "+secretIndex)
}