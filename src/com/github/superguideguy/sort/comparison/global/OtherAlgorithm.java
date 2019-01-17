package com.github.superguideguy.sort.comparison.global;

public class OtherAlgorithm {

	static int[] createArray(int n) {
		int[] ret = new int[n];
		for (int i = 0; i < n; i++) ret[i] = i+1;
		return ret;
	}
	
	static boolean isSorted(int[] arr) {
		boolean ret = true;
		for (int i = 0; i < arr.length - 1; i++) if (arr[i] > arr[i+1]) {
			ret = false;
			break;
		}
		return ret;
	}
	
	static boolean isReverseSorted(int[] arr) {
		boolean ret = true;
		for (int i = 0; i < arr.length - 1; i++) if (arr[i] < arr[i+1]) {
			ret = false;
			break;
		}
		return ret;
	}
	
	/**
	 * Determines whether the array is already sorted or reverse sorted.
	 * If the array is sorted, this algorithm returns true.
	 * If the array is reverse sorted, this algorithm runs the flip(arr) algorithm on the array, and then returns true.
	 * If the array is neither sorted nor reverse sorted, this algorithm returns false.
	 * Runs on average in O(log n) time. Runs at worst in O(n) time. Requires at most O(n) additional memory.
	 * If the array is not reverse sorted, this algorithm is stable. Else, this algorithm is antistable.
	 * 
	 * @param arr 
	 * @return
	 */
	static boolean preCheck(int[] arr) {
		if (isReverseSorted(arr)) {ShufflingAlgorithm.flip(arr); return true;}
		if (isSorted(arr)) return true;
		return false;
	}
	
	/**
	 * Returns the median of the three values provided.
	 * @param a
	 * @param b
	 * @param c
	 * @return
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
