package ca.vgorcinschi.algorithms1_5_17

class ErdosRenyiSpec extends BaseSpec {

  "A 0 passed to count" should "result in 0 connections" in{
    ErdosRenyi count(0) should equal(0)
  }
}
