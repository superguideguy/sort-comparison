package global;

import java.util.Random;

public class ShufflingAlgorithm {
	
	/**
	 * Exchanges the 2 entries of the array provided at the indices provided.
	 * @param arr
	 * @param index1
	 * @param index2
	 */
	static void exchange(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/**
	 * Shuffles the input array via the Fisher-Yates Shuffle.
	 * Runs in O(n) time. Requires at most O(1) space.
	 * @param arr
	 */
	static void shuffleArray(int[] arr) {
		Random r = new Random();
		for (int i = arr.length - 1; i >= 1; i--) {
			int j = r.nextInt(i+1);
			exchange(arr,i,j);
		}
	}
	
	/**
	 * Flips the entire provided array, thus making reverse sorted arrays sorted.
	 * Runs in O(n) time. Requires O(n) additional space.
	 * @param arr
	 */
	static void flip(int[] arr) {
		int[] arr2 = new int[arr.length];
		for (int i = 0; i < arr.length; i++) arr2[i] = arr[arr.length - 1 - i];
		arr = arr2;
	}
}
