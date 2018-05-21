package ca.vgorcinschi.algorithms1_5_19

import ca.vgorcinschi.algorithms1_3_34.ImmutableRandomBag
import ca.vgorcinschi.algorithms1_5_17.InputValidators
import ca.vgorcinschi.algorithms1_5_7.quickUnionUF.QuickUnionUF
import edu.princeton.cs.algs4.{StdDraw, StdOut}

import scala.io.StdIn

object RandomGrid extends App{

  case class Connection(row: Int, col: Int)
  case class Grid(width: Int, height: Int)

  type Cell = (Int, Int)

  //only interested in right and bottom neighbours since we're traversing grid (O = N^2)
  def neighbours: Cell => List[Cell] = cell => List((cell._1, cell._2+1), (cell._1+1, cell._2))

  /*
    note that for any other traversal method (other then N^2) we need to change both
    neighbours and legalNeighbours to account for left and upper neighbours
   */
  def legalNeighbours: (Cell, Grid) => List[Cell] = (cell, grid) => neighbours(cell) filter {case (row, col) => row<grid.width && col<grid.height}

  def generate(upperBound: Int):ImmutableRandomBag[Connection] = {
    var randomBag = ImmutableRandomBag[Connection](None, 0)
    for{row <- 0 until upperBound
        col <- 0 until upperBound}
      {
        legalNeighbours((row, col), Grid(upperBound, upperBound)) map{ case (r,c) => Connection(r, c)} foreach {conn=> randomBag = randomBag.add(conn)}
      }
    randomBag
  }

  def play():Unit ={
    val answer = StdIn.readLine("Please indicate the upper bound for Erdos Renyi algorithm (an Int): \n")
    val upperBound = InputValidators.validateType[Int](answer)

    val bag = generate(upperBound)//get bag of all connections
    val quickUnion = new QuickUnionUF((upperBound+1)*(upperBound+1))//UF is ^2 to nest all connections

    StdDraw.setCanvasSize(400, 400)
    StdDraw.setXscale(0, upperBound)
    StdDraw.setYscale(0, upperBound)
    StdDraw.setPenRadius(0.001)
    StdDraw.setPenColor(StdDraw.BLUE)

    bag.iterator.foreach{
      conn =>
        quickUnion.union(conn.col, conn.row)
        StdDraw.line(conn.col/upperBound+1, conn.col%(upperBound+1),
          conn.row/upperBound+1, conn.col%(upperBound+1))
        StdOut.println(quickUnion.counter())
    }
  }
}
