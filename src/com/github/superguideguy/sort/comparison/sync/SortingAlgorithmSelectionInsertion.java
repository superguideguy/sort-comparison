package com.github.superguideguy.sort.comparison.sync;

public class SortingAlgorithmSelectionInsertion {

	static void insertionSort(int[] arr) {
		synchronized (Runner.task) {
			Runner.task = CurrentTask.INSERTION_SORT;
		}
		TimeKeeper.start();
		
		for (int i = 1; i < arr.length; i++)
			synchronized (arr) {
				for (int j = i; (j > 0) && (arr[j-1] > arr[j]) ; j--) ShufflingAlgorithm.exchange(arr, j, j-1);
			}
		
		TimeKeeper.stop();
		TimeKeeper.commit();
	}
	
	static void selectionSort(int[] arr) {
		synchronized (Runner.task) {
			Runner.task = CurrentTask.SELECTION_SORT;
		}
		TimeKeeper.start();
		
		for (int i = 0; i < arr.length - 1; i++) {
			int lowestIndex = i;
			synchronized (arr) {
				for (int j = i+1; j < arr.length; j++) if (arr[j] < arr[lowestIndex])
					lowestIndex = j;
				ShufflingAlgorithm.exchange(arr, i, lowestIndex);
			}
		}
		
		TimeKeeper.stop();
		TimeKeeper.commit();
	}
	
	
}
