package ca.vgorcinschi.algorithms1_5_7.quickUnionUF

abstract class AbstractUF extends UF{
  
  val array: Array[Int]
   //validate that p is a valid index
   protected def validate(p: Int) = {
		  val l = array.length
      if (p < 0 || p >= l)
			   throw new Error(s"index $p is not between 0 and ${l-1}")
   }
}