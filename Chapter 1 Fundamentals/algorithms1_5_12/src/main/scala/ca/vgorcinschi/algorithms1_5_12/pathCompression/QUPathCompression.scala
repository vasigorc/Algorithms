package ca.vgorcinschi.algorithms1_5_12.pathCompression

import ca.vgorcinschi.algorithms1_5_7.quickUnionUF.QuickUnionUF

import scala.collection.mutable.ListBuffer

case class QUPathCompression(size: Int) extends QuickUnionUF(size){

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