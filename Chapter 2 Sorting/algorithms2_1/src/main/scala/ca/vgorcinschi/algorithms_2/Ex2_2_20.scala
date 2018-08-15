package ca.vgorcinschi.algorithms_2

import scala.reflect.ClassTag

/*
  Exercise 2.2.20 Index sort. Develop and implement a version of mergesort that does not rearange the array
  but returns an int[] array perm such that perm[i] is the index of the ith smallest entry in the array.
 */
class Ex2_2_20 [T : ClassTag : Ordering] extends BaseSort [T]{

  type Indexed = (T, Int)

  def indexSort(a: Array[T]): Array[Int] = {

    val zippedWithIndex: List[Indexed] = a.toList.zipWithIndex
    val tupplesList = msort(zippedWithIndex)
    tupplesList.map(_._2).toArray
  }

  /*
    this part is almost identical with the merge sort function for Lists
    from Odersky, Spoon and Venners' "Programming in Scala" 3rd Edition
   */
  def msort(xs: List[Indexed]): List[Indexed] = {
    def merge(xs: List[Indexed], ys: List[Indexed]): List[Indexed] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1 ,y :: ys1) =>
          if (less(x._1, y._1)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

    val n = xs.length / 2
    if(n==0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(ys), msort(zs))
    }
  }

  override def sort(a: Array[T]): Array[T] = ???
}
