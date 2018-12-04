package ca.vgorcinschi.algorithms2_3

import scala.collection.immutable.Vector
import Ordered._
import scala.annotation.tailrec

/*
  Exercise 2.3.15 Nuts and bolts. (G. J. E. Rawlins) You have a mixed pile of N nuts and N bolts and need to quickly
  find the corresponding pairs of nuts and bolts. Each nut matches exactly one bolt, and each bolt matches exactly one
  nut. By fitting a nut and bolt together, you can see which is bigger, but it is not possible to directly compare two
  nuts or two bolts. Give an efficient method for solving the problem.

  Solution: Pick a random bolt, compare it to all the nuts, find its matching nut and compare it to all the bolts, thus
  splitting the problem into two problems, one consisting of the nuts and bolts smaller than the matched pair and one
  consisting of the larger ones.

  Repeating in this manner yields an algorithm whose expected running time can be analyzed by imitating the known
  analysis for Quicksort.

  There are n! possibilities for matching the nuts and bolts a priori. Every attempted matching between a nut and a bolt
  has three possible outcomes (they match, the nut is larger, the nut is smaller). Ay bounded degree decision tree that
  solves the problem has depth at least log (n!) = Theta (n log n). This is a lower bound for the expected number of
  comparisons in any randomized algorithm.
 */
class NutsAndBolts[T: Ordering](nuts: Vector[T], bolts: Vector[T]) {

  require(nuts.size == bolts.size, "Sizes of bolts and nuts vectors should be equal")

  def sort(): Vector[(T, T)] = {
    sort(nuts, bolts, 0, nuts.size - 1)
  }

  /**
    * since the method will call itself recursively we need the ability to
    * pass lower and upper indexes (for partitioning)
    *
    * @param nuts  - vector containing nuts
    * @param bolts - vector containing bolts
    * @param hi    - upper limit index
    * @param lo    - bottom limit index
    * @return a vector of zipped nuts and bolts
    */
  private def sort(nuts: Vector[T], bolts: Vector[T], hi: Int, lo: Int): Vector[(T, T)] = {
    if (lo >= hi) return nuts zip bolts

    val (newNuts, pivot) = partition(nuts, bolts(hi), lo, hi)
    val (newBolts, _) = partition(bolts, nuts(pivot), lo, hi)

    sort(newNuts, newBolts, lo, pivot - 1) ++ sort(newNuts, newBolts, pivot + 1, hi)
  }

  /**
    *
    * @param input - can be bolts or nuts
    * @param pivot - a bolt or a nut
    * @param lo    - start
    * @param hi    - end
    * @return passed-in vector sorted with passed-in pivot + the new pivot index
    */
  private def partition(input: Vector[T], pivot: T, lo: Int, hi: Int): (Vector[T], Int) = {

    @tailrec
    def loop(i: Int, j: Int, a: Vector[T]): (Vector[T], Int) = {
      if (j >= hi) return (a, i)
      a(j) match {
        case below if below < pivot => loop(i + 1, j, swap(a, i, j))
        case same if same == pivot => loop(i, j - 1, swap(a, j, i))
        case _ => loop(i, j + 1, a)
      }
    }

    val (updatedVector, newPivotIndex) = loop(lo, lo, input)
    //swap values at high and newPivot
    (swap(updatedVector, newPivotIndex, hi), newPivotIndex)
  }

  private def swap(vector: Vector[T], indexA: Int, indexB: Int): Vector[T] = {
    val temp1 = vector(indexA)
    vector.updated(indexA, vector(indexB)).updated(indexB, temp1)
  }
}