package ca.vgorcinschi.algorithms_2

/**
  * 2.2.17 Linked-list sort. Implement a natural mergesort for linked lists. (This is the
  * method of choice for sorting linked lists because it uses no extra space and is
  * guaranteed to be linearithmic.
  *
  * Implementation is inspired from Vladimir Kostyukov's Merge Sort implementation (not bottom-up in his case):
  * @see <a href="https://github.com/vkostyukov/scalacaster/blob/master/src/sort/MergeSort.scala">scalaster.Mergesort</a>
  */
class Ex2_2_17 [T : Ordering]{

  def mergesort(list: List[T]):List[T] = {
    import Ordered._

    def sort(p: (List[T], List[T])):List[T] = p match {
      case (Nil, Nil) => Nil
      case (ha :: Nil, Nil) => ha :: Nil
      case (Nil, hb :: Nil) => hb :: Nil
      case (as, bs) => merge(halfifyAndSort(as), halfifyAndSort(bs))
    }

    def nextSubsequences(as: List[T]) : (List[T], List[T]) = {
      def loop(source: List[T], fs: List[T], ss: List[T]):(List[T], List[T]) = (source, fs, ss) match {
        case (first :: tail, Nil , Nil) => loop(tail, List(first), ss)
        case (first :: tail, _, Nil) => if(first > fs.head) loop(tail, first :: fs, ss) else loop(tail, fs, List(first))
        case (first :: tail, _, _) => if(first > ss.head) loop(tail, fs, first :: ss) else (fs, ss)
        case (Nil, _, _) => (fs.reverse, ss.reverse)
      }

      loop(as, Nil, Nil)
    }

    def merge(as: List[T], bs: List[T]): List[T] = {
      def loop(cs: List[T], ds: List[T], r: List[T]): List[T] = (cs, ds) match {
        case (ha :: ta, hb :: tb) =>
          if (ha < hb) loop(ta, ds, ha :: r)
          else loop(cs, tb, hb :: r)
        case (ha :: ta, Nil) => loop(ta, Nil, ha :: r)
        case (Nil, hb :: tb) => loop(Nil, tb, hb :: r)
        case (Nil, Nil) => r
      }

      loop(as, bs, Nil).reverse
    }

    def halfifyAndSort(as: List[T]) = sort(nextSubsequences(as))

    halfifyAndSort(list)
  }
}
