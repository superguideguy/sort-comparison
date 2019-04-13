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

public class SortingAlgorithmOther {

	static void pigeonholeSort(int[] arr) {
		synchronized (Runner.task) {
			Runner.task = CurrentTask.PIGEONHOLE_SORT;
		}
		TimeKeeper.start();
		
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			synchronized (arr) {
				if (arr[i] > max) max = arr[i];
				if (arr[i] < min) min = arr[i];
			}				
		}
		int[] holes = new int[max-min+1];
		
		for (int i = 0; i < arr.length; i++)
			synchronized (arr) {
				holes[arr[i] - min]++;
			}
		
		int index = 0;
		for (int i = 0; i < holes.length; i++) while (holes[i] > 0) synchronized (arr) {
			holes[i]--;
			arr[index] = i + min;
			index++;
		}
		TimeKeeper.stop();
		TimeKeeper.commit();
	}
	
}
