package ca.vgorcinschi.algorithms2_4.selectionfilter

case class CoordinatePoint(x: Double, y: Double, z: Double)

private object Origin extends CoordinatePoint(0d, 0d, 0d)

object CoordinatePoint {
  /**
   * A [[CoordinatePoint]] is "bigger" than another if it is closer
   * to the coordinates' origin point - [[com.sun.management.VMOption.Origin]] in
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
