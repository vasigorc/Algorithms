package ca.vgorcinschi.algorithms2_4.selectionfilter

/**
 * Exercise 2.4.28 Selection filter
 * Write a program similar to TopM that reads points (x, y, z)
 * from standard input, takes a value M from the command line, and prints
 * the M points that are closest to the origin in Euclidean distance.
 * Estimate the running time of your client N = 10^8 and M=10^4
 */
object SelectionFilter extends App {

  case class CoordinatePoint(x: Double, y: Double, z: Double)

  object Origin extends CoordinatePoint(0d, 0d, 0d)

  /**
   * A [[CoordinatePoint]] is "bigger" than another if it is closer
   * to the coordinates' origin point - [[Origin]] in
   * @see [[https://en.wikipedia.org/wiki/Euclidean_distance#Three_dimensions Euclidean distance]]
   */
  implicit object OriginOrdering extends Ordering[CoordinatePoint] {

    def euclideanDistance(point: CoordinatePoint): Double = {
      math.sqrt(
        math.pow(point.x - Origin.x, 2) +
          math.pow(point.y - Origin.y, 2) +
          math.pow(point.z - Origin.z, 2)
      )
    }

    override def compare(x: CoordinatePoint, y: CoordinatePoint): Int = {
      // inversing the parameters' order to get the smallest value win
      euclideanDistance(y) compare euclideanDistance(x)
    }
  }

}

