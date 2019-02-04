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
	
}
