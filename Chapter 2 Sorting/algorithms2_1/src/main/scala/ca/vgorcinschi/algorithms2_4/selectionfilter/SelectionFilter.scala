package ca.vgorcinschi.algorithms2_4.selectionfilter

import java.io.{BufferedReader, FileReader}
import java.nio.file.{Files, Paths}
import ca.vgorcinschi.algorithms2_4.ArrayMaxPQ
import Parseable._
import CoordinatePoint._

import scala.annotation.tailrec
import scala.io.StdIn
import scala.reflect.classTag

/** Exercise 2.4.28 Selection filter
  * Write a program similar to TopM that reads points (x, y, z)
  * from standard input, takes a value M from the command line, and prints
  * the M points that are closest to the origin in Euclidean distance.
  * Estimate the running time of your client N = 10^8 and M=10^4
  */
object SelectionFilter extends App {

  import Ordered._

  if (args.isEmpty) {
    System.err.println("""ERROR: The name of the file containing 
         points to be plotted is the single required argument
         to the program.""")
    System.exit(1)
  }

  private val fileName: String = args(0)

  if (!Files.exists(Paths.get(fileName))) {
    System.err.println(
      s"ERROR: File $fileName doesn't exist or you don't have necessary permissions to access to it"
    )
    System.exit(1)
  }

  private val max_allowable_pq_size = getMFromUser

  private val bufferedReader = new BufferedReader(new FileReader(fileName))

  // lazy stream, so each line will be read only when stream is consumed
  private val streamOfMaybeCoordinatePoints
    : Stream[Either[String, CoordinatePoint]] = Stream
    .continually(bufferedReader.readLine())
    .takeWhile(_ != null)
    .map(InputParser.parse[CoordinatePoint](_))

  private val resultingQueue: ArrayMaxPQ[CoordinatePoint] =
    arrayMaxPQRunner(
      streamOfMaybeCoordinatePoints,
      (maxPQ: ArrayMaxPQ[CoordinatePoint]) =>
        (either: Either[String, CoordinatePoint]) => consumeOneF(maxPQ, either),
      max_allowable_pq_size
    )(classTag[CoordinatePoint], ReverseOriginOrdering)

  while (!resultingQueue.isEmpty) {
    println(s"${resultingQueue.size()}th point: ${resultingQueue.delMax()}")
  }

  private def consumeOneF(
    maxPQ: ArrayMaxPQ[CoordinatePoint],
    either: Either[String, CoordinatePoint]
  ): Unit = {
    either match {
      case Left(error_input) =>
        System.err.println(
          s"ERROR: Symbol $error_input couldn't be parsed into an instance of ${CoordinatePoint.getClass.getSimpleName}"
        )
        System.exit(1)
      case Right(point) =>
        if (maxPQ.size() < max_allowable_pq_size || point < maxPQ.max()) {
          if (maxPQ.size() == max_allowable_pq_size - 1) {
            println(
              s"Dropping most distant Coordinate Point: ${maxPQ.delMax()}"
            )
          }
          maxPQ.insert(point)
        }
    }
  }

  @tailrec
  def getMFromUser: Int = {
    print("Enter the desired size of the Priority Queue (Integer value):")
    InputParser.parse[Int](StdIn.readLine()) match {
      case Left(invalidInput) =>
        println(s"$invalidInput is not an integer value!")
        getMFromUser
      case Right(value) => value
    }
  }
}
