package ca.vgorcinschi.algorithms1_5_19

import ca.vgorcinschi.algorithms1_3_34.ImmutableRandomBag
import ca.vgorcinschi.algorithms1_5_17.InputValidators
import ca.vgorcinschi.algorithms1_5_7.quickUnionUF.QuickUnionUF
import edu.princeton.cs.algs4.StdDraw

import scala.io.StdIn

object RandomGrid extends App{

  play()

  case class Cell(id: Int, row: Int, col: Int)

  def generate(upperBound: Int):ImmutableRandomBag[Cell] = {
    var idGen: Int = 0

    var randomBag = ImmutableRandomBag[Cell](None, 0)
    for{row <- 0 until upperBound
        col <- 0 until upperBound}
      {
        idGen+=1
        randomBag = randomBag.add(Cell(idGen, row, col))
      }
    randomBag
  }

  def play():Unit ={
    val answer = StdIn.readLine("Please indicate an upper bound (an Int): \n")
    val upperBound = InputValidators.validateType[Int](answer)

    val bag = generate(upperBound)//get bag of all connections
    val quickUnion = new QuickUnionUF(upperBound*upperBound)//UF is ^2 to nest all connections

    StdDraw.setXscale(0, upperBound)
    StdDraw.setYscale(0, upperBound)
    StdDraw.setPenRadius(0.01)
    StdDraw.setPenColor(StdDraw.BLUE)

    bag.iterator.sliding(2,2).withPartial(false) foreach{case Seq(c1, c2) =>
      quickUnion.union(c1.id, c2.id)
      StdDraw.line(c1.row, c1.col, c2.row, c2.col)
      println(s"Nr of sites: ${quickUnion.counter()}")
    }
  }
}
