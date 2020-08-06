package ca.vgorcinschi.algorithms2_4.selectionfilter

/**
 * takes an input string and returns
 * either an instance of the required
 * type (Double, Int, Boolean, CoordinatePoint, etc.)
 * or the initially passed-in string
 * @tparam T - required return type
 */
trait Parseable[T] {
  def parse(input: String): Either[String, T]
}

/**
 * `import Parseable._` before calling `InputParser.parse(input)`
 */
object Parseable {
  implicit val parseDouble = new Parseable[Double] {
    override def parse(input: String): Either[String, Double] = {
      val simplifiedInput = input.replaceAll("[ \\n]", "").toLowerCase
      try {
        Right(simplifiedInput.toDouble)
      } catch {
        case _: NumberFormatException =>
          Left(input)
      }
    }
  }

  implicit val parseInt = new Parseable[Int] {
    override def parse(input: String): Either[String, Int] = {
      val simplifiedInput = input.replaceAll("[ \\n]", "").toLowerCase
      try {
        Right(simplifiedInput.toInt)
      } catch {
        case _: NumberFormatException =>
          Left(input)
      }
    }
  }
}

object InputParser {
  def parse[T](input: String)(implicit p: Parseable[T]): Either[String, T] = p.parse(input)
}