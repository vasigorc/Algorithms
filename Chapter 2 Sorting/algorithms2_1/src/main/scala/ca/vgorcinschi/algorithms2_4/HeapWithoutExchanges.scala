package ca.vgorcinschi.algorithms2_4

import scala.reflect.ClassTag

/**
  * Ex 2.4.26 Heap without exchanges. Because the exch() primitive is used in the sink()
  * and swim() operations, the items are loaded and stored twice as often as necessary.
  * Give more efficient implementations that avoid this inefficiency, à la insertion
  * sort [[ca.vgorcinschi.algorithms2_1.Ex2_1_25]]
  * @param capacity
  * @param tag
  * @param cmp
  * @tparam Key
  */
class HeapWithoutExchanges [Key](val capacity: Int = 10)(implicit tag: ClassTag[Key],
                                 override protected val cmp: Ordering[_ >: Key])
                                 extends MaxPQ[Key]{
  override def insert(v: Key): Unit = ???

  override def max(): Key = ???

  override def delMax(): Key = ???
}