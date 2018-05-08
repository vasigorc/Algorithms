package ca.vgorcinschi.algorithms1_5_17

import org.scalacheck.{Gen, Prop}

class ErdosRenyiProps extends BaseProps {

  property("count"){
    //testing for between 3 and 100 sites
    val tests = Gen.choose(3, 100)
    check(Prop.forAll(tests){
      n=> ErdosRenyi.count(n) == n-1
    })
  }
}
