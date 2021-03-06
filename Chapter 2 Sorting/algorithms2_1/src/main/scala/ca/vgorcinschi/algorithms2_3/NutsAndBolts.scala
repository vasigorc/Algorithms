package ca.vgorcinschi.algorithms2_3

import scala.collection.immutable.Vector
import Ordered._
import scala.annotation.tailrec
import scala.math._

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
    sort(nuts, bolts)
  }

  /**
    * since the method will call itself recursively we need the ability to
    * pass lower and upper indexes (for partitioning)
    *
    * @param nuts  - vector containing nuts
    * @param bolts - vector containing bolts
    * @return a vector of zipped nuts and bolts
    */
  private def sort(nuts: Vector[T], bolts: Vector[T]): Vector[(T, T)] = {
    if (nuts.size <= 1) return nuts zip bolts

    val lo = 0
    val hi = max(lo, nuts.size - 1)

    val (newNuts, pivotIndex) = partition(nuts, bolts(hi), lo, hi)
    val (newBolts, _) = partition(bolts, newNuts(pivotIndex), lo, hi)

    sort(newNuts.slice(lo, pivotIndex), newBolts.slice(lo, pivotIndex)) ++
      sort(newNuts.slice(pivotIndex, hi + 1), newBolts.slice(pivotIndex, hi + 1))
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
    def loop(smaller: Int, equal: Int, larger: Int, a: Vector[T]): (Vector[T], Int) = {
      if (equal > larger) return (a, smaller)
      a(equal) match {
        case below if below < pivot => loop(smaller + 1, equal + 1, larger, exch(a, smaller, equal))
        case same if same == pivot => loop(smaller, equal + 1, larger, a)
        case _ => loop(smaller, equal, larger - 1, exch(a, equal, larger))
      }
    }

    loop(lo, lo, hi, input)
  }

  /**
    * exchages values at two indexes from the passed-in vector
    *
    * @param vector vector
    * @param indexA - first index for swaping values
    * @param indexB - second index for swaping values
    * @return - copy of the initial vector with two values swapped
    */
  private def exch(vector: Vector[T], indexA: Int, indexB: Int): Vector[T] = {
    val temp1 = vector(indexA)
    vector.updated(indexA, vector(indexB)).updated(indexB, temp1)
  }
}

object NutsAndBolts {
  def apply[T: Ordering](nuts: Vector[T], bolts: Vector[T]): NutsAndBolts[T] = new NutsAndBolts[T](nuts, bolts)
}