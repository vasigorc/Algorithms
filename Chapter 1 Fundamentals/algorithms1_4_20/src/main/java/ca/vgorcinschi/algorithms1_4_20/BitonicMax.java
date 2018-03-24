package ca.vgorcinschi.algorithms1_4_20;

/******************************************************************************
 *  Compilation:  javac BitonicMax.java
 *  Execution:    java BitonicMax N 
 *  Dependencies: StdOut.java
 *
 *  Find the maximum in a bitonic array (strictly increasing, then strictly
 *  decreasing) of size N in log N time.
 * 
 *  % java BitonicMax N
 *
 ******************************************************************************/


public class BitonicMax {  

	/*
    find the index of the maximum in a bitonic
    subarray[from...end]. It is the same principle
    as in the basic Binary search only that we are looking for a
    maximum value (inverted logic)
   */
    public static int max(int[] a, int lo, int hi) {
        if (hi == lo) return hi;
        int mid = lo + (hi - lo) / 2;
        if (a[mid] < a[mid + 1]) return max(a, mid+1, hi);
        if (a[mid] > a[mid + 1]) return max(a, lo, mid);
        else return mid;
    } 
}