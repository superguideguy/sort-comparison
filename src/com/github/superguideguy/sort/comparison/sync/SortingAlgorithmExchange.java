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
