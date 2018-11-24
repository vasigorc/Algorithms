package ca.vgorcinschi.algorithms2_3

object Ex2_3_6 extends BaseSortComparesCount {

  performance of quickSortInstance.getClass.getSimpleName in {
    measure method methodName in {
      using(testArrays) in {
        sampleArray =>
          quickSortInstance.sort(sampleArray)
      }
    }
  }
}

//  ::Benchmark QuickSort.less::
//    cores: 8
//  hostname: localhost.localdomain
//  name: Java HotSpot(TM) 64-Bit Server VM
//  osArch: amd64
//  osName: Linux
//  vendor: Oracle Corporation
//    version: 25.161-b12
//  Parameters(Ns -> 100): 680.0 #  2NlnN = 921
//  Parameters(Ns -> 1000): 11611.0 #  2NlnN = 13815
//  Parameters(Ns -> 10000): 164166.0 # 2NlnN = 184206
