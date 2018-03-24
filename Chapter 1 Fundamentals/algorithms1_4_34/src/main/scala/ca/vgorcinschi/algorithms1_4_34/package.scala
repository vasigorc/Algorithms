package ca.vgorcinschi

package object algorithms1_4_34 {
  
  def binary(a: Array[Int], key: Int) = binaryAid(a, key, 0, a.length - 1)

  /*
  standard binary search
 */
  def binaryAid(a: Array[Int], key: Int, lo: Int, hi: Int): Int = {
    if (lo > hi) -1
    else {
      val mid = middle(lo, hi)
      mid match {
        case _ if (key < a(mid)) => binaryAid(a, key, lo, mid - 1)
        case _ if (key > a(mid)) => binaryAid(a, key, mid + 1, hi)
        case _ => mid
      }
    }
  }

  def middle(from: => Int, end: => Int) = from + (end - from) / 2
}