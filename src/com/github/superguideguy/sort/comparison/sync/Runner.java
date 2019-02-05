package com.github.superguideguy.sort.comparison.sync;

import java.util.Arrays;

public class Runner {

	public static int[] arr;
	public static final int ARRAY_SIZE = 10_000;
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
		Report r = new Report(task,TimeKeeper.t_obj,TimeKeeper.t_total,TimeKeeper.sigma_ms_inversion,TimeKeeper.sigma_ms_avgRMS);
		System.out.println(r.toString());
		
		ShufflingAlgorithm.shuffleArray(arr);
		TimeKeeper.clear();
		SortingAlgorithmExchange.cocktailSort(arr);
		r = new Report(task,TimeKeeper.t_obj,TimeKeeper.t_total,TimeKeeper.sigma_ms_inversion,TimeKeeper.sigma_ms_avgRMS);
		System.out.println(r.toString());
		
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
