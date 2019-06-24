package ca.vgorcinschi.algorithms2_4

import scala.reflect.ClassTag

/**
  * Exercise 2.4.22 Array resizing. Add array resizing to MaxPQ, and prove bounds like those of
  * PROPOSITION Q for array accesses, in an amortized sense.
  * @tparam Key - must be comparable
  */
class ResizableMaxPQ[Key: ClassTag : Ordering] extends MaxPQ[Key]() {

  private def arraySize = pq.length
  private def heapSize = N
  private def half = (size: Int) => size >> 1

  override def insert(v: Key): Unit = {
    // +1 because we're using index 1 heap
    if (arraySize == heapSize + 1) {
      pq = pq ++ Array.fill(arraySize)(None)
    }
    super.insert(v)
  }

  override def delMax(): Key = {
    if (heapSize + 1 < half(arraySize)) {
      pq = pq slice(0, half(arraySize))
    }
    super.delMax()
  }
}