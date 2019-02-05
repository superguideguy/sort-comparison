package com.github.superguideguy.sort.comparison.sync;

public class SortingAlgorithmExchange {

	static void bubbleSort(int[] arr) {
		synchronized (Runner.task) {
			Runner.task = CurrentTask.BUBBLE_SORT;
		}
		TimeKeeper.start();
		
		while (!OtherAlgorithm.isSorted(arr))
			for (int i = 0; i < arr.length - 1; i++) if (arr[i] > arr[i+1])
				synchronized (arr) {
					ShufflingAlgorithm.exchange(arr, i, i+1);
				}
		
		TimeKeeper.stop();
		TimeKeeper.commit();
	}
	
	static void cocktailSort(int[] arr) {
		synchronized (Runner.task) {
			Runner.task = CurrentTask.COCKTAIL_SORT;
		}
		TimeKeeper.start();
		
		while (!OtherAlgorithm.isSorted(arr)) {
			for (int i = 0; i < arr.length - 1; i++) if (arr[i] > arr[i+1])
				synchronized (arr) {
					ShufflingAlgorithm.exchange(arr, i, i+1);
				}
			for (int i = arr.length - 2; i >= 0; i--) if (arr[i] > arr[i+1])
				synchronized (arr) {
					ShufflingAlgorithm.exchange(arr, i, i+1);
				}
		}
			
		
		TimeKeeper.stop();
		TimeKeeper.commit();
	}
	
}
