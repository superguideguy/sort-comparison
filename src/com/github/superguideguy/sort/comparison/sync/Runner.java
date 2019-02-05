package com.github.superguideguy.sort.comparison.sync;

import java.util.Arrays;

public class Runner {

	public static int[] arr;
	public static final int ARRAY_SIZE = 100_000;
	public static CurrentTask task = CurrentTask.WAIT;
	
	public static void main(String[] args) {
		//Create additional threads
		//new Thread(new WatchdogInternal()).start();
		//new Thread(new GUI()).start();
		new Thread(new TimeKeeper()).start();
		
		//Create array
		arr = OtherAlgorithm.createArray(ARRAY_SIZE);
		sum(arr);
		
		//Run through test battery, and record
		ShufflingAlgorithm.shuffleArray(arr);
		TimeKeeper.clear();
		SortingAlgorithmSelectionInsertion.shellsortA168604(arr);
		try { Thread.sleep(100); } catch (Exception e) { }
		Report.miniReport(task, TimeKeeper.t_cumm, "COMPLETE " + (TimeKeeper.current_avgRMS + TimeKeeper.current_inversions));
		
		
		ShufflingAlgorithm.shuffleArray(arr);
		TimeKeeper.clear();
		SortingAlgorithmOther.pigeonholeSort(arr);
		try { Thread.sleep(100); } catch (Exception e) { }
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
	
	public static void sum(int[] arr) {
		long check = 0;
		for (int i = 0; i < arr.length; i++) {
			check+=arr[i];
		}
		System.out.println(check);
	}
	
}
