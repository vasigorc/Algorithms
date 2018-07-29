package ca.vgorcinschi.algorithms_2

import scala.collection.immutable.Queue
import scala.util.Try

/**
  * 2.2.17 Linked-list sort. Implement a natural mergesort for linked lists. (This is the
  * method of choice for sorting linked lists because it uses no extra space and is
  * guaranteed to be linearithmic.
  *
  * Implementation is inspired from Vladimir Kostyukov's Merge Sort implementation (not bottom-up in his case):
  *
  * @see <a href="https://github.com/vkostyukov/scalacaster/blob/master/src/sort/MergeSort.scala">scalaster.Mergesort</a>
  */
class Ex2_2_17 [T : Ordering]{

  def mergesort(list: List[T]):List[T] = {
    import Ordered._
    implicit def queueToList(q: Queue[T]): List[T] = Option(q).fold(List.empty[T]) {_ toList}
    case class TupleAndRemainder(tuple: (List[T], List[T]), remainder: List[T])

    def nextSubsequences(as: List[T]) : (List[T], List[T]) = {
      def loop(source: List[T], fs: List[T], ss: List[T]):(List[T], List[T]) = (source, fs, ss) match {
        case (first :: tail, Nil , Nil) => loop(tail, List(first), ss)
        case (first :: tail, _, Nil) => if(first >= fs.head) loop(tail, first :: fs, ss) else loop(tail, fs, List(first))
        case (first :: tail, _, _) => if(first >= ss.head) loop(tail, fs, first :: ss) else (fs.reverse, ss.reverse)
        case (Nil, _, Nil) => (fs.reverse, ss)
        case (Nil, _, _) => (fs.reverse, ss.reverse)
      }

      loop(as, Nil, Nil)
    }

    def tus(bs: List[T]): TupleAndRemainder = {
      val (left, right) = nextSubsequences(bs)
      val remainder = Try(bs.drop(left.size + right.size)).getOrElse(List.empty[T])
      TupleAndRemainder((left, right), remainder)
    }

    def merge(left: List[T], right: List[T]): List[T] = (left, right) match {
      case (x :: xs, y :: ys) if x <= y => x :: merge(xs, right)
      case (x :: xs, y :: ys) => y :: merge(left, ys)
      case _ => if (left.isEmpty) right else left
    }

    def bottomUpSort(as: List[T]): List[T] = {
      def loop(subsequences: (List[T], List[T]), unsorted: List[T], sorted: Queue[T]) :List[T] = {
        if(subsequences._1.size == as.size) subsequences._1
        else{
          val mergedPart: List[T] = (merge _).tupled(subsequences)
          if(mergedPart.size == as.size) mergedPart
          else {
            unsorted match {
              case Nil =>
                val newSorted = merge(sorted, mergedPart)
                val tupleAndRemainder: TupleAndRemainder = tus(newSorted)
                loop(tupleAndRemainder.tuple, tupleAndRemainder.remainder, Queue.empty[T])
              case xs =>
                val tupleAndRemainder: TupleAndRemainder = tus(unsorted)
                loop(tupleAndRemainder.tuple, tupleAndRemainder.remainder, Queue(merge(sorted, mergedPart): _*))
            }
          }
        }
      }

      val firstTuple = nextSubsequences(as)
      loop(firstTuple, as.drop(firstTuple._1.size + firstTuple._2.size), Queue.empty[T])
    }

    bottomUpSort(list)
  }
}