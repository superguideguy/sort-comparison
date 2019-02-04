package com.github.superguideguy.sort.comparison.sync;

public class Runner {

	public static int[] arr;
	public static final int ARRAY_SIZE = 100_000;
	public static CurrentTask task = CurrentTask.WAIT;
	
	public static void main(String[] args) {
		//Create additional threads
		new Thread(new WatchdogInternal()).start();
		new Thread(new GUI()).start();
		
		//Create array
		arr = OtherAlgorithm.createArray(ARRAY_SIZE);
		
		//Run through test battery, and record
		
		//Run through test battery, just for visuals
		
	}
	
	/**
	 * Exists to prevent compiler optimization of various synchronized blocks.
	 */
	public static void nop() {
		return;
	}
	
}
