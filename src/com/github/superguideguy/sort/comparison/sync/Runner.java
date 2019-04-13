/*********************************************************************
* Copyright (c) 2019 superguideguy
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package com.github.superguideguy.sort.comparison.sync;

import java.util.Arrays;

public class Runner {

	public static int[] arr, copy;
	public static final int ARRAY_SIZE = 10_000_000;
	public static CurrentTask task = CurrentTask.WAIT;
	
	public static void main(String[] args) {
		//Create additional threads
		//new Thread(new WatchdogInternal()).start();
		//new Thread(new GUI()).start();
		new Thread(new TimeKeeper()).start();
		
		for (int i = 0; i < 1; i++) {
		
		//Create array
		arr = OtherAlgorithm.createArray(ARRAY_SIZE);
		copy = new int[ARRAY_SIZE];
		sum(arr);
		
		//Run through test battery, and record
		ShufflingAlgorithm.shuffleArray(arr);
		TimeKeeper.clear();
		SortingAlgorithmSelectionInsertion.selectionSort(arr);
		try { Thread.sleep(5); } catch (Exception e) { }
		TimeKeeper.update();
		Report.miniReport(task, TimeKeeper.t_cumm, "COMPLETE " + (TimeKeeper.current_avgRMS + TimeKeeper.current_inversions));
		
		} 
		
	/*	TimeKeeper.clear();
		for (int i = 0; i < arr.length; i++)
			arr[i] = copy[i];
		SortingAlgorithmExchange.cocktailSort(arr);
		try { Thread.sleep(5); } catch (Exception e) { }
		TimeKeeper.update();
		Report.miniReport(task, TimeKeeper.t_cumm, "COMPLETE " + (TimeKeeper.current_avgRMS + TimeKeeper.current_inversions));
		*/
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
