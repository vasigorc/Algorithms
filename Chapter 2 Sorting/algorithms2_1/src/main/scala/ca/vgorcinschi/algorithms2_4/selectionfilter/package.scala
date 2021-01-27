package ca.vgorcinschi.algorithms2_4

import scala.reflect.ClassTag

package object selectionfilter {
  def arrayMaxPQFromRunner[R, I <: Stream[R], V](
    input: I,
    consumeOneF: ArrayMaxPQ[V] => R => Unit,
    size: Int
  )(implicit tag: ClassTag[V], cmp: Ordering[_ >: V]): ArrayMaxPQ[V] = {
    val pq = new ArrayMaxPQ[V](size)
    input.foreach(r => consumeOneF(pq)(r))
    pq
  }
}
