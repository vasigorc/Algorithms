import scala.util.Random

//off-top
//let a be {0,1,2,3}
def shuffleArray(a: Array[Int]):Array[Int]={

  //returns indices for new Array, e.g. {3,1, 2, 0}
  def permutationIndices(filledIndex: Int, collector: Array[Int]):Array[Int]={
    if(filledIndex == a.length-1)
      collector
    else{
      val randomIndex = Random.nextInt(a.length)
      if(!collector.contains(randomIndex)){
        collector(filledIndex)=randomIndex
        permutationIndices(filledIndex+1, collector)
      }
      permutationIndices(filledIndex, collector)
    }
  }

  def swapValues(someArray: Array[Int], ind1: Int, ind2: Int): Array[Int] ={
    val tmp = someArray(ind1)
    someArray(ind1) = ind2
    someArray(ind2) = tmp
    someArray
  }

  val collector = permutationIndices(0, new Array(a.length))
  (a /: collector){case (initial, collectorIndex)=>
    val arrReplaceIndex = collectorIndex
    val arrContraReplaceIndex = collector(collectorIndex)
    swapValues(collector, arrReplaceIndex, arrContraReplaceIndex)
    swapValues(a, arrReplaceIndex, arrContraReplaceIndex)
  }
}

shuffleArray(Array(0,1,2,3))