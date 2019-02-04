package com.github.superguideguy.sort.comparison.sync;

public class OtherAlgorithm {

	/**
	 * Creates an array of size array_size
	 * 
	 * @param array_size The size of the array to be created
	 * @return A sorted array of length array_size.
	 * 
	 * Runs in O(n) time.
	 * Requires O(n) memory.
	 * Stability not applicable.
	 */
	static int[] createArray(int array_size) {
		int[] ret = new int[array_size];
		for (int i = 0; i < array_size; i++) ret[i] = i+1;
		return ret;
	}
	
	
	/**
	 * 
	 * @param arr
	 * @return
	 * 
	 * Runs on average in O(log n) time. Runs at worst in O(n) time.
	 * Requires O(1) additional memory.
	 * Stability not applicable.
	 */
	static boolean isSorted(int[] arr) {
		boolean ret = true;
		for (int i = 0; i < arr.length; i++) {
			synchronized (arr) {
				if (arr[i] > arr[i+1]) {
					ret = false;
					break;
				}
			}
		}
		return ret;
	}
	
	/**
	 * 
	 * @param arr
	 * @return
	 * 
	 * Runs on average in O(log n) time. Runs at worst in O(n) time.
	 * Requires O(1) additional memory.
	 * Stability not applicable.
	 */
	static boolean isReverseSorted(int[] arr) {
		boolean ret = true;
		for (int i = 0; i < arr.length; i++) {
			synchronized (arr) {
				if (arr[i] < arr[i+1]) {
					ret = false;
					break;
				}
			}
		}
		return ret;
	}
	
	/**
	 * Returns the median of the three values provided.
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 * 
	 * Runs in O(1) time.
	 * Requires O(1) additional memory.
	 * Stability not applicable.
	 */
	static int median(int a, int b, int c) {
		if (a < b) {
			if (b < c) return b;
			if (a < c) return c;
			return a;
		} else {
			if (a < c) return a;
			if (b < c) return c;
			return b;
		}
	}
	
}
