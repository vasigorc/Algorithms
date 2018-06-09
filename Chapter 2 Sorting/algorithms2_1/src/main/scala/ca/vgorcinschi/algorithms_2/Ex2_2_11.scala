package ca.vgorcinschi.algorithms_2

class Ex2_2_11[T<: Ordered[T]] extends MergeSort[T] {

  override def sort(a: Array[T], lo: Int, hi: Int): Array[T] = {
    if(hi <= lo) a
    if(a.length <= 15) {
      val shell = new Ex2_1_11
      shell.sort(a)
    }
    else super.sort(a, lo, hi)
  }
}
