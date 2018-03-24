package ca.vgorcinschi.algorithms1_5_7.quickUnionUF

class WeightedQuickUnionUF (size: Int) extends QuickUnionUF(size) {
  
  val sizes = (0 to size).toArray
  
  override def performUnion(rootP: Int, rootQ: Int) = {
    if(sizes(rootP) < sizes(rootQ)){
    	  array(rootP) = rootQ
    	  sizes(rootQ) += sizes(rootP)        
      } else {
        array(rootQ) = rootP
    	  sizes(rootP) += sizes(rootQ)
      }
  }
}