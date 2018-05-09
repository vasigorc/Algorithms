package ca.vgorcinschi.algorithms1_5_17

import ca.vgorcinschi.algorithms1_5_7.quickUnionUF.QuickUnionUF

import scala.io.StdIn

object ErdosRenyi extends App {

  play()

  def count(n: Int):Int = {
    import Generator._

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

  def play():Unit ={
    val answer = StdIn.readLine("Please indicate the upper bound for Erdos Renyi algorithm (an Int): \n")
    val upperBound = InputValidators.validateType[Int](answer)
    println(s"Quick Union finished. ${count(upperBound)} connections performed")
  }
}