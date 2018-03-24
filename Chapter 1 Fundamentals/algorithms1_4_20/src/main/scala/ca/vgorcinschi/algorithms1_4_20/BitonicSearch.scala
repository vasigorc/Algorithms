package ca.vgorcinschi.algorithms1_4_20

import scala.concurrent.Future
import scala.util.{Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by vgorcinschi on 25/04/17.
  */
class BitonicSearch {

  def isIntInArray(a: Array[Int], key: Int) = {
    val (first, second) = a.splitAt(BitonicMax.max(a, 0, a.length-1))

    val firstThread = Future {binary(first, key)}
    val secondThread = Future {binary(second, key)}

    val result = firstThread.zipWith(secondThread){
      case (a, b) => if(a > b)
                        ("ascending", a)
                      else
                        ("descending", b)
    }
    
    result onComplete {
      case Success((str, int)) => {
         if(int == -1) 
        	 println(s"$key couldn't be found in the array")
         else
           println(s"$key was found in the $str array at index $int")           
      }
      case Failure(t) => println(s"An error has occured: ${t.getMessage}")
    }
  }

  def binary(a: Array[Int], key: Int) = binaryAid(a, key, 0, a.length-1)

  /*
  standard binary search
 */
  def binaryAid(a: Array[Int], key: Int, lo: Int, hi: Int):Int = {
    if(lo > hi) -1
    else{
      val mid = middle(lo, hi)
      mid match {
        case _ if (key < a(mid)) => binaryAid(a, key, lo, mid-1)
        case _ if(key > a(mid)) => binaryAid(a, key, mid+1, hi)
        case _ => mid
      }
    }
  }
  
  def middle(from: => Int, end: => Int) = from + (end - from) / 2
}