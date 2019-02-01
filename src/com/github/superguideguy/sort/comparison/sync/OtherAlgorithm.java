package com.github.superguideguy.sort.comparison.sync;

import static com.github.superguideguy.sort.comparison.sync.WatchdogExternal.isPaused;

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
	 * Affected by _WatchdogExternal.isPaused_ attribute.
	 */
	static int[] createArray(int array_size) {
		int[] ret = new int[array_size];
		boolean isPausedLocal = false;
		for (int i = 0; i < array_size; i++) {
			synchronized (isPaused) {
				isPausedLocal = isPaused;
			}
			if (isPausedLocal) {
				i--;
				continue;
			}
			
			ret[i] = i+1;
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
	 * Affected by _WatchdogExternal.isPaused_ attribute.
	 */
	static boolean isSorted(int[] arr) {
		boolean ret = true;
		boolean isPausedLocal = false;
		for (int i = 0; i < arr.length; i++) {
			synchronized (isPaused) {
				isPausedLocal = isPaused;
			}
			if (isPausedLocal) {
				i--;
				continue;
			}
			
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
	 * Affected by _WatchdogExternal.isPaused_ attribute.
	 */
	static boolean isReverseSorted(int[] arr) {
		boolean ret = true;
		boolean isPausedLocal = false;
		for (int i = 0; i < arr.length; i++) {
			synchronized (isPaused) {
				isPausedLocal = isPaused;
			}
			if (isPausedLocal) {
				i--;
				continue;
			}
			
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
	 * *NOT* affected by _WatchdogExternal.isPaused_ attribute.
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
	
	/**
	 * Determines whether the array is already sorted or reverse sorted.
	 * If the array is sorted, this algorithm returns true.
	 * If the array is reverse sorted, this algorithm runs the flip(arr) algorithm on the array, and then returns true.
	 * If the array is neither sorted nor reverse sorted, this algorithm returns false.
	 * 
	 * @param arr 
	 * @return
	 * 
	 * Runs on average in O(log n) time. Runs at worst in O(n) time.
	 * Requires at most O(n) additional memory.
	 * If the array is not reverse sorted, this algorithm is stable. Else, this algorithm is antistable.
	 * Affected by _WatchdogExternal.isPaused_ attribute.
	 */
	static boolean preCheck(int[] arr) {
		//if (isReverseSorted(arr)) {ShufflingAlgorithm.flip(arr); return true;}
		if (isSorted(arr)) return true;
		return false;
	}
}
