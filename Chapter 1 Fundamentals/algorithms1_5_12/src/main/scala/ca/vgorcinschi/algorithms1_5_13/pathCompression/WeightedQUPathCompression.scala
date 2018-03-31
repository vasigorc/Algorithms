package ca.vgorcinschi.algorithms1_5_13.pathCompression

import ca.vgorcinschi.algorithms1_5_7.quickUnionUF.WeightedQuickUnionUF

import scala.collection.mutable.ListBuffer

class WeightedQUPathCompression (size: Int) extends WeightedQuickUnionUF (size) {

  override def find(p: Int): Int = {
    var tmp = p
    var intermediaries = ListBuffer[Int]()

    while(tmp != array(tmp)){
      tmp = array(tmp)
      intermediaries += tmp
    }
    intermediaries foreach {array(_) = tmp}
    tmp
  }
}
