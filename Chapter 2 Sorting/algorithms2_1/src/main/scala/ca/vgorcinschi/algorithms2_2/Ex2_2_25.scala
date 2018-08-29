package ca.vgorcinschi.algorithms2_2

import ca.vgorcinschi.algorithms2_1.BaseSort

import scala.annotation.tailrec
import scala.reflect.ClassTag

/*
  Exercise 2.2.25 Multiway mergesort. Develop a mergesort implementation based on the idea of doing k-way merges (rather
  than 2-way merges). Analyze your algorithm, develop a hypothesis regarding the best value of k, and run experiments to
  validate your hypothesis
 */
class Ex2_2_25 [T : ClassTag : Ordering] extends BaseSort[T]{

  def ksort(a: Array[T], k: Int): Array[T] = {
    val e = a.length
    if(k > e) return a //proceed to merge stage at this point
    /*
      Else for every k we need k-1 division points between 0 and e

      To partition the target array into equal segments we need to find the almost exact length of a segment, i.e: e/k | round
      Last segment will always be a little longer/shorter for an odd e or an odd k - which is ok
     */
    val divisor: Int = math.round(e.toDouble/k).toInt

    val indices: List[Int] = 0 to e by divisor toList //generate list of "milestones"
    var previous = indices.head
    val current: List[Array[T]] = for (m <- indices.tail) yield {
      val unsorted = a.slice(previous, m)
      previous = m
      ksort(unsorted, k)
    }
    merge(current, Array.empty)
  }

  //reduce all subarrays into an accumulator
  @tailrec
  private def merge(cur: List[Array[T]], acc: Array[T]): Array[T] = cur match {
    case Nil => acc
    case ab::Nil => acc ++: ab
    case _ =>
      import Ordered._
      val withIndex = cur.zipWithIndex
      val max = (withIndex.head /: withIndex.tail) {
        (n, f) => if(n._1.head > f._1.head) n
        else f
      }
      val nextCur = cur(max._2).tail :: cur.slice(0, max._2) ::: cur.slice(max._2, cur.length)
      merge(nextCur.filter(_.nonEmpty), acc ++: max._1)
  }

  override def sort(a: Array[T]): Array[T] = ???
}