package ca.vgorcinschi.algorithms1_5_19

import ca.vgorcinschi.algorithms1_3_34.ImmutableRandomBag
import ca.vgorcinschi.algorithms1_5_19.RandomGrid.Cell

class RandomBagSpec extends BaseSpec {

  trait RandomBag{
    val begin = new ImmutableRandomBag[Cell](None, 0)
  }

  "Random Bag's iterator" should "contain all items passed to parent iterable" in new RandomBag{
    val connections = List(Connection(0,1), Connection(1,0), Connection(1,1))
    var localRB = begin
    for(c <- connections) localRB = localRB.add(c)
    assert(localRB.iterator.forall(connections.contains(_)) == true)
  }

  "Empty Bag" should "return another bag with size 1" in new RandomBag {
    val bag = begin.add(Connection(0,1))
    bag.size should equal(1)
  }
}
