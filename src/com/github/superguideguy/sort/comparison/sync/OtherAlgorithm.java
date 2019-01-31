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
	synchronized static int[] createArray(int array_size) {
		int[] ret = new int[array_size];
		for (int i = 0; i < array_size; i++) ret[i] = i+1;
		return ret;
	}
	
	
	static boolean isSorted(int[] arr) {
		boolean ret = true;
		synchronized (arr) {
			for (int i = 0; i < arr.length - 1; i++) if (arr[i] > arr[i+1]) {
				ret = false;
				break;
			}
		}
		return ret;
	}
}
