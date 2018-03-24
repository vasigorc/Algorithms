import ca.vgorcinschi.algorithms1_4_34.validators.InputValidators._
import scala.io.{StdIn}
import ca.vgorcinschi.algorithms1_4_34.hotandcold.HotAndColdImpl

object Main extends App {
  
  println("Hello, let's play some \"Hot and Cold\"!")
  playTheGame(true)
  
  
  def playTheGame(repeat: Boolean):Unit ={
		if (repeat){
		  
		  val ubInput = StdIn.readLine("What is the upper limit of the range" 
       +" you wish to guess.\n")
		  val upperBound = validateType[Int](ubInput)
		  new HotAndColdImpl(upperBound).play
		  //repeat game?
		  repeatGame()
		} else {
		  println("Thank you for playing\"Hot and Cold\"," + 
	        "come back again whenever you want!")
		}
  }
 
  
  def repeatGame() = {
    val answer = StdIn.readLine("Would you like to try again (Y/N) ?\n")
    val decision = validateType[Boolean](answer)
    playTheGame(decision)
  }
}
