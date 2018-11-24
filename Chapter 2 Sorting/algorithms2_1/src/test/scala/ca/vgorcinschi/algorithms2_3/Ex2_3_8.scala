package ca.vgorcinschi.algorithms2_3

import org.scalameter.api.Gen

/**
  * Ex 2.3.8 About how many compares will Quick.sort() make when sorting an array of
  * N items that are all equal
  */
object Ex2_3_8 extends BaseSortComparesCount {

  override protected val testArrays: Gen[Array[Int]] = NsGen.map(n => Array.fill(n)(1))

  performance of quickSortInstance.getClass.getSimpleName in {
    measure method methodName in {
      using(testArrays) in {
        sampleArray =>
          quickSortInstance.sort(sampleArray)
      }
    }
  }
}

//::Benchmark QuickSort.less::
//cores: 8
//hostname: localhost.localdomain
//name: Java HotSpot(TM) 64-Bit Server VM
//osArch: amd64
//osName: Linux
//vendor: Oracle Corporation
//version: 25.161-b12
//Parameters(Ns -> 100): 564.0 #
//Parameters(Ns -> 1000): 8898.0 #
//Parameters(Ns -> 10000): 121034.0 #