package ca.vgorcinschi.algorithms_2

//Segewick and Wayne's implementation re-written in Scala
class MergeSort [T <: Ordered[T]] extends BaseSort [T]{
  var aux: Array[T] = _

  def sort(a: Array[T]): Array[T] = {
    aux = a
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
      if(i > mid) a(k) = {
        j+=1
        aux(j)
      } // left half exausted - take from the right
      else if(j > hi) a(k) = {
        i+=1
        aux(i)
      } // right half exausted - take from the left
      else if(less(aux(j), aux(i))){
        j+=1
        a(k)=aux(j)
      } // current key on right less - take from the right
      else{
        i+=1
        a(k)=aux(i)
      }  // current key on left less - take from the left
    })
    a
  }
}
