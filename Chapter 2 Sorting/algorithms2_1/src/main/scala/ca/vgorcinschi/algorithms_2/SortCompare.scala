package ca.vgorcinschi.algorithms_2

import ca.vgorcinschi.algorithms_1.Stopwatch

import scala.util.Random

object SortCompare extends App{

  private val alg1: String = args(0)
  private val alg2: String = args(1)
  private val N: Int = Integer.parseInt(args(2))
  private val T: Int = Integer.parseInt(args(3))

  private val t1: Double = timeRandomInput(alg1, N, T)
  private val t2: Double = timeRandomInput(alg2, N, T)
  print(f"For $N%d random Doubles\n $alg1%s is")
  println(f" ${t2/t1}%f faster than $alg2%s")

  def time(alg: String, a: Array[Double]):Double={
    val timer = new Stopwatch()
    factoryMethod(alg).sort(a)
    timer.elapsedTime()
  }

  def timeRandomInput(alg: String, N: Int, T: Int):Double ={
    var total = 0.0D
    val a = new Array[Double](N)
    for{t <- 0 until T
        i <- 0 until N}{
      a(i) = Random.nextDouble()
      total += time(alg, a)
    }
    total
  }

  private def factoryMethod(alg: String):BaseSort[Double] = alg match {
    case "Insertion" => new InsertionSort[Double]
    case "Ex2_1_25" => new Ex2_1_25[Double]
    case v @ _ => throw new NotImplementedError(s"$v sort hasn't been added to the factory method yet.")
  }
}
