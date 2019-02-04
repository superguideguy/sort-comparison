package com.github.superguideguy.sort.comparison.sync;

import java.util.Random;

public class ShufflingAlgorithm {

	/**
	 * 
	 * @param arr
	 * @param index1
	 * @param index2
	 */
	synchronized static void exchange(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/**
	 * 
	 * @param arr
	 */
	synchronized static void shuffleArray(int[] arr) {
		Random r = new Random();
		for (int i = arr.length - 1; i >= 1; i--) {
			int j = r.nextInt(i+1);
			exchange(arr,i,j);
		}
	}
	
	/**
	 * 
	 * @param arr
	 */
	synchronized static void flip(int[] arr) {
		int[] arr2 = new int[arr.length];
		for (int i = 0; i < arr.length; i++) arr2[i] = arr[arr.length - 1 - i];
		arr = arr2;
	}
	
}
