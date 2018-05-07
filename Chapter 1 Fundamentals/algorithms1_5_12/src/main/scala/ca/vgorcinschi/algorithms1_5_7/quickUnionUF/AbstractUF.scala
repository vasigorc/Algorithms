package ca.vgorcinschi.algorithms1_5_7.quickUnionUF

/*
 * takes the initial number of components
 * as its argument
 */
abstract class AbstractUF(private var count: Int) extends UF{
  
  override def counter() = count
  def count (n: Int): Unit = {count = n}
  
  val array: Array[Int]
   //validate that p is a valid index
   protected def validate(p: Int) = {
		  val l = array.length
      if (p < 0 || p >= l)
			   throw new Error(s"index $p is not between 0 and ${l-1}")
   }
}