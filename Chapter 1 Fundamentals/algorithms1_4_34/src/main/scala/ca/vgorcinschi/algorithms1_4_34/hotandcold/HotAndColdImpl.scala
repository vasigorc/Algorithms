package ca.vgorcinschi.algorithms1_4_34.hotandcold

import ca.vgorcinschi.algorithms1_4_34._
import ca.vgorcinschi.algorithms1_4_34.validators.InputValidators._
import scala.io.{StdIn}

class HotAndColdImpl(upperBound: Int) extends {
  val secret = scala.util.Random.nextInt(upperBound-1)
} with HotAndCold {
  
  private val array = (0 to upperBound).toArray
  private lazy val secretIndex = binary(array, secret)  
  /*
   * the main logic of the application goes into this method
   */
  override def guess(attempt: Int):Unit = {
    counter+=1
    val guessIndex = binary(array, attempt)
    guessIndex match {
      case -1 => {
         val nextAttempt = StdIn.readLine(s"Please be attentive $attempt is outside the search range" 
	          +" (0 to $upperBound). Try again: \n");
         val a = validateType[Int](nextAttempt)
         guess(a)
      }
      case x if (secretIndex == x) => println("Congratulations! You have guessed" 
	                  +" the secret number "+secret+" "+counter+" guesses.")
      case x if (counter > 1) => {
        if(absDiff(previousIndex) < absDiff(guessIndex))
          println("Colder...")
        else
          println("Hotter ...")
        previousIndex = guessIndex
        guess(validateType[Int](StdIn.readLine("Try again ... \n")))
      }
      case _ => previousIndex = guessIndex; guess(validateType[Int](StdIn.readLine("Try again ... \n")))
    }
  }
  
  def absDiff(a: Int):Int = scala.math.abs(secretIndex - a)
  
  override def play = guess(validateType[Int](StdIn.readLine("Your first try: \n")))
}