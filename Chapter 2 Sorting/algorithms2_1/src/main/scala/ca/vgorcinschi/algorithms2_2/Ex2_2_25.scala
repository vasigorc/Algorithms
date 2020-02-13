package ca.vgorcinschi.algorithms2_2

import ca.vgorcinschi.algorithms2_1.BaseSort
import org.scalameter
import org.scalameter.Measurer.{MemoryFootprint, MethodInvocationCount}
import org.scalameter.execution.invocation.InvocationCountMatcher

import scala.annotation.tailrec
import scala.reflect.ClassTag
import math._

/*
  Exercise 2.2.25 Multiway mergesort. Develop a mergesort implementation based on the idea of doing k-way merges (rather
  than 2-way merges). Analyze your algorithm, develop a hypothesis regarding the best value of k, and run experiments to
  validate your hypothesis
 */
class Ex2_2_25 [T : ClassTag : Ordering] extends BaseSort[T]{

  def ksort(a: Array[T], k: Int): Array[T] = {
    val e = a.length
    if(a.isEmpty || e == 1) return a
    if(k > e) ksort(a, k - 1)
    /*
      Else for every k we need k-1 division points between 0 and e

      To partition the target array into equal segments we need to find the almost exact length of a segment, i.e: e/k | round
      Last segment will always be a little longer/shorter for an odd e or an odd k - which is ok
     */
    val divisor: Int = math.max(round(e.toDouble/k).toInt, 1)

    val indices: List[Int] = indicesGen(e, divisor) toList //generate list of "milestones"
    var previous = indices.head
    val current: List[Array[T]] = for (m <- indices.tail) yield {
      val unsorted = a.slice(previous, m)
      previous = m
      ksort(unsorted, k)
    }
    merge(current, Array.empty)
  }

  private def indicesGen(e: Int, divisor: Int): IndexedSeq[Int] = {
    val range: Range = 0 to e by divisor
    if (!range.contains(e)) range :+ e
    else range
  }

  //reduce all subarrays into an accumulator
  @tailrec
  private def merge(cur: List[Array[T]], acc: Array[T]): Array[T] = cur match {
    case Nil => acc
    case ab::Nil => acc ++ ab
    case _ =>
      import Ordered._
      val withIndex = cur.zipWithIndex
      val min = (withIndex.head /: withIndex.tail) {
        (n, f) => if(n._1.head < f._1.head) n
        else f
      }
      val nextCur = cur(min._2).tail :: cur.slice(0, min._2) ::: cur.slice(min._2 + 1, cur.length)
      merge(nextCur.filter(_.nonEmpty), acc :+ min._1.head)
  }

  // default - two-way merge
  override def sort(a: Array[T]): Array[T] = ???
}

object Ex2_2_25 extends App{

  import scala.util.Random
  import org.scalameter._

  val instance = new Ex2_2_25[Int]
  val useCase = (for (i <- 1 to 1000) yield Random.nextInt(1000)).toArray
  val keys = Array(2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 18, 20, 50, 57, 80, 90)
  val sortName = "Multiway Mergesort"

  //duration test
  keys.foreach{k =>
    //ScalaMeter Warmer objects run the benhcmarked code until detecting steady state
    val time = withWarmer(new scalameter.Warmer.Default) measure {
      instance.ksort(useCase, k)
    }
    println(s"$sortName sorted Int array of size ${useCase.length} with $k way merge in $time")
  }
  /*
    This yields:
      Multiway Mergesort sorted Int array of size 1000 with 2 way merge in 3.953223 ms
      Multiway Mergesort sorted Int array of size 1000 with 3 way merge in 5.565851 ms
      Multiway Mergesort sorted Int array of size 1000 with 4 way merge in 6.751919 ms
      Multiway Mergesort sorted Int array of size 1000 with 5 way merge in 3.880378 ms
      Multiway Mergesort sorted Int array of size 1000 with 6 way merge in 3.551565 ms
      Multiway Mergesort sorted Int array of size 1000 with 7 way merge in 4.243155 ms
      Multiway Mergesort sorted Int array of size 1000 with 8 way merge in 4.840327 ms
      Multiway Mergesort sorted Int array of size 1000 with 9 way merge in 4.74045 ms
      Multiway Mergesort sorted Int array of size 1000 with 10 way merge in 4.150856 ms
      Multiway Mergesort sorted Int array of size 1000 with 12 way merge in 10.185781 ms
      Multiway Mergesort sorted Int array of size 1000 with 15 way merge in 6.783799 ms
      Multiway Mergesort sorted Int array of size 1000 with 18 way merge in 7.92397 ms
      Multiway Mergesort sorted Int array of size 1000 with 20 way merge in 8.585972 ms
      Multiway Mergesort sorted Int array of size 1000 with 50 way merge in 38.804586 ms
      Multiway Mergesort sorted Int array of size 1000 with 57 way merge in 45.286704 ms
      Multiway Mergesort sorted Int array of size 1000 with 80 way merge in 60.434039 ms
      Multiway Mergesort sorted Int array of size 1000 with 90 way merge in 62.598225 ms

      Basically at 10-k algorithm starts to slow down, at k-s 80-90 it is ~ 20 times
      longer then when merging with 2,5 or 6 way merge
   */

  //memory footprint test
  keys.foreach {k=>
    val memoryFootPrint: Quantity[Double] = withMeasurer(new MemoryFootprint) measure {
      instance.ksort(useCase, k)
    }
    println(s"$sortName sorted Int array of size ${useCase.length} with $k way merge with $memoryFootPrint")
  }

  /*
    foot print doesn't depend on number of k's - it is ~4Mb for all runs
   */
}