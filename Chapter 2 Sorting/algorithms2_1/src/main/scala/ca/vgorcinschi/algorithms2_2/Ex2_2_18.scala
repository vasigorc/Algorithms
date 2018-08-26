package ca.vgorcinschi.algorithms2_2

import scala.reflect.ClassTag
import scala.util.Random

/*
  Ex 2.2.18 Shuffling a linked list. Develop and implement a divide-and-conquer algorithm
  that randomly shuffles a linked list in liearithmic time and logarithmic extra space.
 */
class Ex2_2_18 [Item : ClassTag : Ordering]{

  def shuffle(list: List[Item]): List[Item] ={

    val result = new Array[Item](list.size)
    //generate random indices
    val randomIndices = Random.shuffle[Int, IndexedSeq](list.indices).toArray

    list.zip(randomIndices).foreach{ case (item ,i) =>
      result(i) = item
    }
    result.toList
  }
}