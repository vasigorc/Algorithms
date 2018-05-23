package ca.vgorcinschi.algorithms1_5_19

import ca.vgorcinschi.algorithms1_3_34.ImmutableRandomBag
import ca.vgorcinschi.algorithms1_5_17.InputValidators
import ca.vgorcinschi.algorithms1_5_7.quickUnionUF.QuickUnionUF
import edu.princeton.cs.algs4.{StdDraw, StdOut}

import scala.io.StdIn

object RandomGrid extends App{

  play()

  case class Connection(row: Int, col: Int)

  def generate(upperBound: Int):ImmutableRandomBag[Connection] = {
    var randomBag = ImmutableRandomBag[Connection](None, 0)
    for{row <- 0 until upperBound
        col <- 0 until upperBound}
      {
        randomBag = randomBag.add(Connection(row, col))
      }
    randomBag
  }

  def play():Unit ={
    val answer = StdIn.readLine("Please indicate an upper bound (an Int): \n")
    val upperBound = InputValidators.validateType[Int](answer)

    val bag = generate(upperBound)//get bag of all connections
    val quickUnion = new QuickUnionUF(upperBound*upperBound)//UF is ^2 to nest all connections

    StdDraw.setCanvasSize(400, 400)
    StdDraw.setXscale(0, upperBound)
    StdDraw.setYscale(0, upperBound)
    StdDraw.setPenRadius(0.001)
    StdDraw.setPenColor(StdDraw.BLUE)

    bag.iterator.foreach{
      conn =>
        quickUnion.union(conn.col, conn.row)
        StdDraw.line(conn.col/upperBound, conn.col%upperBound,
          conn.row/upperBound, conn.col%upperBound)
        StdOut.println(quickUnion.counter())
    }
  }
}
