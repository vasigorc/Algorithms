package ca.vgorcinschi.algorithms_2

import scala.reflect.ClassTag

//Segewick and Wayne's implementation re-written in Scala
class MergeSort [T : ClassTag : Ordering] extends BaseSort [T]{
  var aux: Array[T] = _

  def sort(a: Array[T]): Array[T] = {
    aux = new Array[T](a.length)
    sort(a, 0, a.length-1)
  }

  protected def sort(a: Array[T], lo: Int, hi: Int): Array[T] = {
    if(hi <= lo) a
    else {
      val mid = lo + (hi - lo)/2
      sort(a, lo, mid)
      sort(a, mid+1, hi)
      merge(a, lo, mid, hi)
    }
  }

  def merge(a: Array[T], lo: Int, mid: Int, hi: Int):Array[T] = {
    var i = lo
    var j = mid+1
    //copy all passed-in items into auxiliary array
    (lo to hi).foreach(k=> {aux(k) = a(k)})

    (lo to hi).foreach(k=>{
      if(i > mid){
        a(k) = aux(j)
        j+=1
      } // left half exausted - take from the right
      else if(j > hi) {
        a(k) = aux(i)
        i+=1
      } // right half exausted - take from the left
      else if(less(aux(j), aux(i))){
        a(k)=aux(j)
        j+=1
      } else { // current key on right less - take from the right
        a(k)=aux(i)
        i+=1
      }  // current key on left less - take from the left
    })
    a
  }
}
