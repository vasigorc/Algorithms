
def arrayPermutations:Array[Int]=>Traversable[Array[Int]] = {
  case Array() => List[Array[Int]](Array[Int]())
  case arr =>
    for {
      (elem, index) <- arr.zipWithIndex
      perm <- arrayPermutations(arr.take(index) ++ arr.drop(1+index))
    } yield {
      elem +: perm
    }
}

def shuffleArray(a: Array[Int])={
  arrayPermutations(a).filter(perm => perm.zip(a).forall{
    case (left: Int, right: Int) => left != right
  }).head
}

shuffleArray(Array(0,1,2,3))