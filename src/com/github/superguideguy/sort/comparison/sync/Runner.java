package com.github.superguideguy.sort.comparison.sync;

public class Runner {

	public static int[] arr;
	public static final int ARRAY_SIZE = 100_000;
	
	public static void main(String[] args) {
		//Create Watchdog threads
		new Thread(new WatchdogExternal()).start();
		new Thread(new WatchdogInternal()).start();
		
		//Create array
		arr = OtherAlgorithm.createArray(ARRAY_SIZE);
		
		//Create GUI thread
		
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
