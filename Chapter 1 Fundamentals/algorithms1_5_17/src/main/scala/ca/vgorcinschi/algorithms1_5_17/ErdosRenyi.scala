package ca.vgorcinschi.algorithms1_5_17

import ca.vgorcinschi.algorithms1_5_7.quickUnionUF.QuickUnionUF

object ErdosRenyi extends App {

  import Generator._

  def count(n: Int):Int = {

    var connections = 0
    val randomPairs = pairs(positiveInts(n), positiveInts(n))
    val uf = new QuickUnionUF(n)
    val stream = streamOfRandom(randomPairs)
    for{
      //for n sites there could be a max of n-1 union connections
      (l, r) <- stream.takeWhile(_ => connections < n-1)
      if(!uf.connected(l, r))
    } {
      uf.union(l, r)
      connections+=1
    }
    connections
  }
}
