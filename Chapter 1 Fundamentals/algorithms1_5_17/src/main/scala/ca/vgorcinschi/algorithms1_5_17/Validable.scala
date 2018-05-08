package ca.vgorcinschi.algorithms1_5_17

import scala.io.StdIn
import scala.util.{Failure, Success, Try}

trait Validable[T] {
  def validate(input: String): T
}

object Validable {
  implicit val boolean = new Validable[Boolean] {
    def validate(input: String): Boolean = {
      val simplifiedInput = input.replaceAll("[ \\n]", "").toLowerCase()

      if(simplifiedInput.matches("[yn]")){
        simplifiedInput match {
          case "y" => true
          case "n" => false
        }
      }else{
        validate(StdIn.readLine(s"$simplifiedInput is not a valid "
          +"answer, please try again\n"))
      }
    }
  }

  implicit val int = new Validable[Int]{
    def validate(input: String): Int = {
      val simplifiedInput = input.replaceAll("[ \\n]", "").toLowerCase()

      val attempt = Try(simplifiedInput toInt)
      attempt match {
        case Success(response) => response
        case Failure(e) => validate(StdIn.readLine(s"$simplifiedInput is not a valid "
          +"answer, please try again\n"))
      }
    }
  }
}