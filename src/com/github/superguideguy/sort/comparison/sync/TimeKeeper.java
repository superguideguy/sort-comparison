package com.github.superguideguy.sort.comparison.sync;

import java.time.Duration;
import java.time.Instant;

public class TimeKeeper implements Runnable {

	static Duration t_total, t_cumm;
	static Instant t_begin, t_end;
	
	static long sigma_ms_inversion = 0;
	static double sigma_ms_avgRMS = 0, current_avgRMS = 0;
	static int correct_begin = 0, correct_end = 0;
	
	public static final int objectiveB = Runner.ARRAY_SIZE+1, objectiveE = Runner.ARRAY_SIZE+1;
	public static boolean objectiveMet = false;
	public static int callGUI = 0;
	
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
				callGUI++;
				if (callGUI >= 10) {
					GUI.update();
					callGUI -= 10;
				}
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
		synchronized (t_cumm) {
			t_cumm = t_cumm.plus(Duration.between(t_begin, t_end));
		}
	}
	
	static void commit() {
		t_total = t_cumm.plus(Duration.ZERO);
	}
	
	static void update() {
		//Inversions
		boolean previousIsDecreasing = false, currentIsDecreasing = false;
		for (int i = 0; i < Runner.arr.length - 1; i++) {
			if (i > i + 1) currentIsDecreasing = true;
			if (i < i + 1) currentIsDecreasing = false;
			if (currentIsDecreasing != previousIsDecreasing) sigma_ms_inversion++;
			previousIsDecreasing = currentIsDecreasing;
		}
		
		//RMS
		current_avgRMS = 0;
		double z = 0;
		for (int i = 0; i < Runner.arr.length; i++) {
			double x = Runner.arr[i] - Runner.arr[Runner.arr[i]-1];
			z += Math.pow(x, 2);
		}
		z = z / Runner.arr.length;
		z = Math.sqrt(z);
		current_avgRMS = z;
		sigma_ms_avgRMS += current_avgRMS;
		
		//Correct beginning/ending
		correct_begin = 0;
		for (int i = 0; i < Runner.arr.length; i++) {
			if (Runner.arr[i] == i+1) correct_begin++;
			else break;
		}
		for (int i = Runner.arr.length - 1; i >= 0; i--) {
			if (Runner.arr[i] == i+1) correct_end++;
			else break;
		}
		if ((correct_begin >= objectiveB) || (correct_end >= objectiveE)) objectiveMet = true;
		
	}
	
}
