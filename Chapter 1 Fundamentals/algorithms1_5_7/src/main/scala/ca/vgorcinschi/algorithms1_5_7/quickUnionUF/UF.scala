package ca.vgorcinschi.algorithms1_5_7.quickUnionUF

trait UF {
  
  var count:Int = 0
  //returns the number of components
  def counter():Int = count
  //component identifier for p(0 to N-1)
  def find(p: Int):Int
  //returns true if the two sites are in the same component
  def connected(p: Int, q: Int):Boolean
  //add connection between p and q
  def union(p: Int, q: Int)
}