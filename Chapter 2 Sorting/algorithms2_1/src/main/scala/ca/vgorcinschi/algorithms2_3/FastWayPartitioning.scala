package ca.vgorcinschi.algorithms2_3

import ca.vgorcinschi.algorithms2_1.BaseSort

import scala.reflect.ClassTag
import scala.util.Random

abstract class FastWayPartitioning[T : ClassTag : Ordering] extends BaseSort [T]{

  override def sort(a: Array[T]): Array[T] = {
    val shuffledArray = Random.shuffle(a.toSeq).toArray
    qSort(shuffledArray)
  }

  private def qSort(array: Array[T]): Array[T] = {
    if (cutoffPredicate(array)) cutoffAction(array)
    else {
      pivotSwap(array)
      val pivot = array.head

      def loop(source: Array[T], accumulator: ArraySections): ArraySections = {
        if (source.isEmpty) return accumulator
        val tail = source.tail
        source.head match {
          case v if v == pivot => loop(tail, accumulator copy(equ = accumulator.equ :+ pivot))
          case v if less(v, pivot) => loop(tail, accumulator copy(lt = accumulator.lt :+ v))
          case v @ _ => loop(tail, accumulator copy(gt = accumulator.gt :+ v))
        }
      }

      val arraySections = loop(array.tail, ArraySections(equ = Array(pivot)))
      qSort(arraySections.lt) ++ arraySections.equ ++ qSort(arraySections.gt)
    }
  }

  protected def cutoffPredicate: Array[T] => Boolean

  protected def cutoffAction: Array[T] => Array[T]

  //optional, thus providing default implementation
  protected def pivotSwap(array: Array[T]): Unit = {}

  case class ArraySections(lt: Array[T] = Array.empty, equ: Array[T], gt: Array[T] = Array.empty)
}