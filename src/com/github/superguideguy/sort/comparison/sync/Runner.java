package com.github.superguideguy.sort.comparison.sync;

import java.util.Arrays;

public class Runner {

	public static int[] arr;
	public static final int ARRAY_SIZE = 25_000;
	public static CurrentTask task = CurrentTask.WAIT;
	
	public static void main(String[] args) {
		//Create additional threads
		//new Thread(new WatchdogInternal()).start();
		//new Thread(new GUI()).start();
		new Thread(new TimeKeeper()).start();
		
		//Create array
		arr = OtherAlgorithm.createArray(ARRAY_SIZE);
		
		//Run through test battery, and record
		ShufflingAlgorithm.shuffleArray(arr);
		TimeKeeper.clear();
		SortingAlgorithmExchange.bubbleSort(arr);
		Report.miniReport(task, TimeKeeper.t_cumm, "COMPLETE " + (TimeKeeper.current_avgRMS + TimeKeeper.current_inversions));
		
		ShufflingAlgorithm.shuffleArray(arr);
		TimeKeeper.clear();
		SortingAlgorithmExchange.cocktailSort(arr);
		Report.miniReport(task, TimeKeeper.t_cumm, "COMPLETE " + (TimeKeeper.current_avgRMS + TimeKeeper.current_inversions));
		
		ShufflingAlgorithm.shuffleArray(arr);
		TimeKeeper.clear();
		SortingAlgorithmSelectionInsertion.selectionSort(arr);
		Report.miniReport(task, TimeKeeper.t_cumm, "COMPLETE " + (TimeKeeper.current_avgRMS + TimeKeeper.current_inversions));
		
		ShufflingAlgorithm.shuffleArray(arr);
		TimeKeeper.clear();
		SortingAlgorithmSelectionInsertion.insertionSort(arr);
		Report.miniReport(task, TimeKeeper.t_cumm, "COMPLETE " + (TimeKeeper.current_avgRMS + TimeKeeper.current_inversions));
		
		System.exit(0);
		
		//Run through test battery, just for visuals
		
	}
	
	/**
	 * Exists to prevent compiler optimization of various synchronized blocks.
	 */
	public static void nop() {
		return;
	}
	
}
