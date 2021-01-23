package ca.vgorcinschi.algorithms2_4.selectionfilter

import cats.implicits._

/** takes an input string and returns
  * either an instance of the required
  * type (Double, Int, Boolean, CoordinatePoint, etc.)
  * or the initially passed-in string
  *
  * @tparam T - required return type
  */
trait Parseable[T] {
  def parse(input: String): Either[String, T]
}

/** `import Parseable._` before calling `InputParser.parse(input)`
  */
object Parseable {
  implicit val parseDouble: Parseable[Double] = new Parseable[Double] {
    override def parse(input: String): Either[String, Double] = {
      val simplifiedInput = input.replaceAll("[ \\n]", "").toLowerCase
      try Right(simplifiedInput.toDouble)
      catch {
        case _: NumberFormatException =>
          Left(input)
      }
    }
  }

  implicit val parseInt: Parseable[Int] = new Parseable[Int] {
    override def parse(input: String): Either[String, Int] = {
      val simplifiedInput = input.replaceAll("[ \\n]", "").toLowerCase
      try Right(simplifiedInput.toInt)
      catch {
        case _: NumberFormatException =>
          Left(input)
      }
    }
  }

  implicit val parseCoordinatePoint: Parseable[CoordinatePoint] =
    (input: String) => {
      val simplifiedInput = input.replaceAll("[ \\n]", "").toLowerCase
      val sequence: Either[String, List[Double]] =
        simplifiedInput.split(",")
          .toList
          .map(parseDouble.parse)
          .sequence
      sequence match {
        case Left(value) => Left(value)
        case Right(doublePoints) =>
          Right(
            CoordinatePoint(
              doublePoints.head,
              doublePoints(1),
              doublePoints(2)
            )
          )
      }
    }
}

object InputParser {
  def parse[T](input: String)(implicit p: Parseable[T]): Either[String, T] =
    p.parse(input)
}
