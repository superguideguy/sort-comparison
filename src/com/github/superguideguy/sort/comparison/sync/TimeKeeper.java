package com.github.superguideguy.sort.comparison.sync;

import java.time.Duration;
import java.time.Instant;

public class TimeKeeper implements Runnable {

	static Duration t_total, t_cumm;
	static Instant t_begin, t_end;
	static long sigma_ms_inversion = 0;
	static double sigma_ms_avgRMS = 0, current_avgRMS = 0;
	static int correct_begin = 0, correct_end = 0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) try {
			Thread.sleep(1);
			synchronized (Runner.task) {
				if (Runner.task == CurrentTask.SHUFFLE) continue;
				if (Runner.task == CurrentTask.WAIT) continue;
			}
			synchronized (Runner.arr) {
				stop();
				update();
				start();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			WatchdogInternal.haltInterrupted();
		}
	}
	
	static void start() {
		t_begin = Instant.now();
	}
	
	static void stop() {
		t_end = Instant.now();
		t_cumm = t_cumm.plus(Duration.between(t_begin, t_end));
	}
	
	static void commit() {
		t_total = t_cumm.plus(Duration.ZERO);
	}
	
	static void update() {
		boolean previousIsDecreasing = false, currentIsDecreasing = false;
		for (int i = 0; i < Runner.arr.length - 1; i++) {
			if (i > i + 1) currentIsDecreasing = true;
			if (i < i + 1) currentIsDecreasing = false;
			if (currentIsDecreasing != previousIsDecreasing) sigma_ms_inversion++;
			previousIsDecreasing = currentIsDecreasing;
		}
	}
	
}
