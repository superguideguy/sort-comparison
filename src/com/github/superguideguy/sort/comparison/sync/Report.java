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

import java.time.Duration;

public class Report {

	private CurrentTask task;
	private Duration t_first, t_total;
	private long sum_inversions;
	private double sum_RMS;
	
	Report(CurrentTask task, Duration t_obj, Duration t_total, long inversions, double RMS) {
		this.task = task;
		t_first = t_obj;
		this.t_total = t_total;
		sum_inversions = inversions;
		sum_RMS = RMS;
	}
	
	public String toString() {
		StringBuilder ret = new StringBuilder("");
		ret = ret.append(task.name()).append(":\t");
		ret = ret.append(t_first.toMillis()).append("ms\t");
		ret = ret.append(t_total.toMillis()).append("ms\t");
		ret = ret.append(sum_inversions).append("msInversions\t");
		ret = ret.append(sum_RMS).append("msPositions\t");
		return new String(ret);
	}
	
	public static void miniReport(CurrentTask task, Duration t_current, String s) {
		String x = "" + task.name() + ":\t" + t_current.toMillis() + "ms\t" + s;
		System.out.println(x);
	}
	
}
