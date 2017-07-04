import scala.io.Source
import ca.vgorcinschi.algorithms1_5_7.quickUnionUF.QuickUnionUF
import scala.util.matching.Regex

object Main extends App {
  val allLines = Source.fromFile("src/main/resources/mediumUF.txt").getLines().toList
  //get & print the initial number of components
  val size = allLines.head.trim.toInt
  println(s"Le nombre initial de composants est $size")

  val uf = new QuickUnionUF(size)

  val Integer = new Regex("(-)?(\\d+)")

  for (line <- allLines.tail) {
    val p = Integer findFirstIn line

    assert(p.isDefined)
    
    val intP = p.get toInt
    val q = Integer findFirstIn line.substring(p.get.length).replace("\\s+", "")

    assert(q.isDefined)

    val intQ = q.get.toInt
    if (!uf.connected(intP, intQ)) {
      uf.union(intP, intQ)
      println(s"L`union fait sur $intP et $intQ")
    }
  }
}