package ca.vgorcinschi.algorithms1_4_34.validators
import scala.io.{StdIn}
import scala.util.{Try, Success, Failure}
import scala.reflect.runtime.universe

object InputValidators {
  
  def validateType[T](input: String)(implicit validable: Validable[T]): T = 
      validable.validate(input)
  
  def isTypeValid(c: Class[_], input: String):Boolean = {
    val simplifiedInput = input.replaceAll("[ \\n]", "").toLowerCase()
      c.getSimpleName match {
      case "boolean" => simplifiedInput.matches("[yn]")
      case "int" => {
        val attempt = Try(simplifiedInput toInt)
        attempt match {
          case Success(_) => true
          case Failure(e) => false
        }
      }
    }
  }
}