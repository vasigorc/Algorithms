package ca.vgorcinschi.algorithms1_5_7.quickUnionUF

import ca.vgorcinschi.algorithms1_5_12.pathCompression.QUPathCompression

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
    
    val rootP = find(p)
    val rootQ = find(q)

      if (rootP != rootQ)
      performUnion(rootP, rootQ)
      count(counter()-1)
  }
  
  def performUnion(rootP: Int, rootQ: Int) = array(rootP) = rootQ 
}

object QuickUnionUF{

  def apply(kind: String) = kind match {
    case "weighted" => new WeightedQuickUnionUF(_)
    case _ => new QUPathCompression(_)
  }
}