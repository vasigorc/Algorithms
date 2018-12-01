package ca.vgorcinschi.algorithms2_3

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
object NutsAndBolts extends App {


}
