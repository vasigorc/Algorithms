package ca.vgorcinschi.algorithms1_4_20

import org.scalatest.{FunSuite, Matchers}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalacheck.{Gen, Arbitrary}
import org.scalacheck.Prop.forAll

class BitonicSearchTests extends FunSuite 
with GeneratorDrivenPropertyChecks with Matchers{
  
  def sortedArray = Gen.containerOf[Array, Int](
      Gen.chooseNum(Int.MinValue, Int.MaxValue) suchThat { _ < 100 }
  ).map { _.sorted}
  
  def bitonicArray = for {
    one <- sortedArray
    two <- sortedArray.map { _.reverse }
  } yield one ++ two
  
  test("test Bitonic Search"){
    forAll (bitonicArray) {
      ar => val result = new BitonicSearch().binary(ar, 1)
      result shouldNot(equal(-1))
    }
  }
}