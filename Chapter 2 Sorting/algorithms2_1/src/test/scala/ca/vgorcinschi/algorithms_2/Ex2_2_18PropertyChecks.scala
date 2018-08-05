package ca.vgorcinschi.algorithms_2

import ca.vgorcinschi.BasePropertyChecks
import org.scalacheck.Prop.BooleanOperators

class Ex2_2_18PropertyChecks extends BasePropertyChecks{

  lazy val listShuffler = new Ex2_2_18[Int]

  test("All lists's should have the same length but be not equal once shuffled"){
    forAll {(list: List[Int]) =>
      var shuffledList = listShuffler.shuffle(list)
      shuffledList should have size list.size
      if(list.nonEmpty && list.size > 1)
        shuffledList should not equal list
    }
  }
}
