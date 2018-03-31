import ca.vgorcinschi.algorithms1_5_7.quickUnionUF.{QuickUnionUF, WeightedQuickUnionUF}

import scala.io.Source
import scala.util.matching.Regex

object Main extends App {
  val allLines = Source.fromFile("src/main/resources/mediumUF.txt").getLines().toList
  //get & print the initial number of components
  val size = allLines.head.trim.toInt

  println(s"Le nombre initial de composants est $size")

  val clArg = args(0)

  val uf = QuickUnionUF(clArg)(size)

  val integerRegex = new Regex("(-)?(\\d+)")

  for (line <- allLines.tail) {
    val p = integerRegex findFirstIn line

    assert(p.isDefined)

    val intP = p.get toInt
    val q = integerRegex findFirstIn line.substring(p.get.length).replace("\\s+", "")

    assert(q.isDefined)

    val intQ = q.get.toInt

    if (!uf.connected(intP, intQ)) {
      uf.union(intP, intQ)
      println(s"L`union fait sur $intP et $intQ")
      println(s"Le nombre des composants est maintenant - ${uf.counter}")
    }
  }
  println(s"\n\tLe tableau final:\n${uf.array.mkString(", ")}")
}