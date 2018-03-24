package ca.vgorcinschi.algorithms1_5_7.quickUnionUF

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class QuickUnionUF(size: Int) extends AbstractUF(size) {

  override val array = (0 to size).toArray

  override def find(p: Int): Int = {
    validate(p)
    Stream.iterate(p)(p => array(p)).dropWhile { _ != array(p) }.head
  }

  override def connected(p: Int, q: Int): Boolean = find(p) == find(q)

  override def union(p: Int, q: Int) = {
    /*
     * need to declare Futures outisde
     * for-comprehension loop to run in parallel
     */
    
    val fRootP = Future { find(p) }
    val fRootQ = Future { find(q) }

    for {
      rootP <- fRootP
      rootQ <- fRootQ
      if (rootP != rootQ)
    } {
      performUnion(rootP, rootQ)
      count -= 1
    }
  }
  
  def performUnion(rootP: Int, rootQ: Int) = array(rootP) = rootQ 
}