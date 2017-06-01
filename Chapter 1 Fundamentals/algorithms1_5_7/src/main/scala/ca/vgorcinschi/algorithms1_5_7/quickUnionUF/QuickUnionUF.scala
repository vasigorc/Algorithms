package ca.vgorcinschi.algorithms1_5_7.quickUnionUF

import rx.lang.scala.Observable

class QuickUnionUF(size: Int)extends AbstractUF{
  
  override val array = (0 to size).toArray
  
  override def find(p: Int):Int = {
    validate(p)
    if(p != array(p))
      find(array(p))
    p
  }
  
  override def connected(p: Int, q: Int):Boolean = find(p) == find(q)
  
  override def union(p: Int, q: Int) = {
    //add future or rx
    //val rootP = find(p)
    //val rootQ = find(q)
    //if(rootP != rootQ){
       //array(rootP) = rootQ
       //count -= 1
  //}
    val a = obsFind(p) zip obsFind(q) filter { case(p,q) => p != q }
    a.subscribe((a)=>{
      array(a._1) = a._2
      count -= 1
    })
  }
  
  def obsFind(i:Int):Observable[Int] = Observable.just(i)
}