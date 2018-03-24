import ca.vgorcinschi.algorithms1_4_20.BitonicSearch

object Main extends App {
  val ar = Array(1,15,17,19,24,42,57,50,43,17,16,3)
  new BitonicSearch().isIntInArray(ar, 42)
}