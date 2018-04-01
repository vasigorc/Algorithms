package ca.vgorcinschi.algorithms1_5_7

class QuickUnionUF(size: Int) extends AbstractUF(size) {

  type Response = Int
  
  override val array = (0 to size).toArray

  override def find(p: Int): Int = {
    validate(p)
    Stream.iterate(p)(p => array(p)).dropWhile { _ != array(p) }.head
  }

  override def union(p: Int, q: Int) = {
    
    val rootP = find(p)
    val rootQ = find(q)

      if (rootP != rootQ)
      performUnion(rootP, rootQ)
      count -= 1
  }
  
  def performUnion(rootP: Int, rootQ: Int) = array(rootP) = rootQ 
}