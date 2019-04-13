package com.github.superguideguy.sort.comparison.sync;

public class SortingAlgorithmSelectionInsertion {

	static void selectionSort(int[] arr) {
		synchronized (Runner.task) {
			Runner.task = CurrentTask.SELECTION_SORT;
		}
		TimeKeeper.start();
		
		for (int i = 0; i < arr.length - 1; i++) {
			int lowestIndex = i;
			//try { TimeKeeper.stop(); Thread.sleep(0,500000); TimeKeeper.start(); } catch (Exception e) { }
			for (int j = i+1; j < arr.length; j++) if (arr[j] < arr[lowestIndex])
				lowestIndex = j;
			synchronized (arr) {
				ShufflingAlgorithm.exchange(arr, i, lowestIndex);
			}
		}
		
		TimeKeeper.stop();
		TimeKeeper.commit();
	}
	
	static void insertionSort(int[] arr) {
		synchronized (Runner.task) {
			Runner.task = CurrentTask.INSERTION_SORT;
		}
		TimeKeeper.start();
		
		for (int i = 1; i < arr.length; i++) for (int j = i; (j > 0) && (arr[j-1] > arr[j]) ; j--)
			synchronized (arr) {
				 ShufflingAlgorithm.exchange(arr, j, j-1);
			}
		
		TimeKeeper.stop();
		TimeKeeper.commit();
	}
	
	static void shellsortA168604(int[] arr) {
		synchronized (Runner.task) {
			Runner.task = CurrentTask.SHELLSORT_A168604_2K_1;
		}
		TimeKeeper.start();
		//2^k-1
		int exp, gap;
		exp = (int) Math.floor( Math.log(arr.length)/Math.log1p(1) );
		
		for (int i = exp; i > 0; i--) {
			gap = (int) Math.pow(2, i)-1;
			for (int j = gap; j < arr.length; j++) for (int k = j; (k >= gap) && (arr[k-gap] > arr[k]) ; k-=gap) {
				synchronized (arr) {
					 ShufflingAlgorithm.exchange(arr, k, k-gap);
				}
			}
		}
		TimeKeeper.stop();
		TimeKeeper.commit();
	}
	
	/**
	 * @deprecated not ready yet
	 * @param arr
	 */
	static void shellsortA003586(int[] arr) {
		synchronized (Runner.task) {
			Runner.task = CurrentTask.SHELLSORT_A003586_3SMOOTH;
		}
		TimeKeeper.start();
		//3smooth
		int exp, gap;
		exp = (int) Math.floor( Math.log(arr.length)/Math.log1p(1) );
		
		for (int i = exp; i > 0; i--) {
			gap = (int) Math.pow(2, i)-1;
			for (int j = gap; j < arr.length; j++) {
				synchronized (arr) {
					for (int k = j; (k >= gap) && (arr[k-gap] > arr[k]) ; k-=gap) ShufflingAlgorithm.exchange(arr, k, k-gap);
				}
			}
		}
		TimeKeeper.stop();
		TimeKeeper.commit();
	}
	
}
