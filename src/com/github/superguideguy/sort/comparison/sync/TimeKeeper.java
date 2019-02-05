package com.github.superguideguy.sort.comparison.sync;

import java.time.Duration;
import java.time.Instant;

public class TimeKeeper implements Runnable {

	static Duration t_total, t_obj, t_cumm;
	static Instant t_begin, t_end;
	
	static long sigma_ms_inversion = 0, current_inversions = 0;
	static double sigma_ms_avgRMS = 0, current_avgRMS = 0;
	static int correct_begin = 0, correct_end = 0;
	
	public static int objectiveB = 1_000, objectiveE = 1_000;
	public static int objBcurrent = objectiveB, objEcurrent = objectiveE;
	public static int callGUI = 0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) try {
			Thread.sleep(1000);
			synchronized (Runner.task) {
				if (Runner.task == CurrentTask.SHUFFLE) continue;
				if (Runner.task == CurrentTask.WAIT) continue;
			}
			synchronized (Runner.arr) {
				stop();
				update();
				callGUI++;
				if (callGUI >= 1) {
					//GUI.update();
					report();
					callGUI -= 1;
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
		if (!(t_obj instanceof Duration)) t_obj = t_total;
	}
	
	static void update() {
		//Inversions
		current_inversions = 0;
		boolean previousIsDecreasing = false, currentIsDecreasing = false;
		for (int i = 0; i < Runner.arr.length - 1; i++) {
			if (Runner.arr[i] > Runner.arr[i+1]) currentIsDecreasing = true;
			if (Runner.arr[i] < Runner.arr[i+1]) currentIsDecreasing = false;
			if (currentIsDecreasing != previousIsDecreasing){
				current_inversions++;
				sigma_ms_inversion++;
			}
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
		if (correct_begin >= objBcurrent) {
			objBcurrent = correct_begin - (correct_begin % objectiveB);
			objBcurrent += objectiveB;
			Report.miniReport(Runner.task, t_cumm, "FIRST " + correct_begin + " SORTED");
		}
		
		correct_end = 0;
		for (int i = Runner.arr.length - 1; i >= 0; i--) {
			if (Runner.arr[i] == i+1) correct_end++;
			else break;
		}
		if (correct_end >= objEcurrent) {
			objEcurrent = correct_end - (correct_end % objectiveE);
			objEcurrent += objectiveE;
			Report.miniReport(Runner.task, t_cumm, "LAST " + correct_end + " SORTED");
		}
		
	}
	
	public static void clear() {
		t_total = Duration.ZERO;
		t_cumm = Duration.ZERO;
		t_begin = Instant.now();
		t_end = Instant.now();
		
		sigma_ms_inversion = 0;
		sigma_ms_avgRMS = 0;
		current_avgRMS = 0;
		correct_begin = 0;
		correct_end = 0;
		
		objEcurrent = objectiveE;
		objBcurrent = objectiveB;
		callGUI = 0;
	}
	
	static void report() {
		Report.miniReport(Runner.task, t_cumm, "FIRST " + correct_begin + " SORTED");
		Report.miniReport(Runner.task, t_cumm, "LAST " + correct_end + " SORTED");
		Report.miniReport(Runner.task, t_cumm, "" + current_inversions + " INVERSIONS");
		Report.miniReport(Runner.task, t_cumm, "AVERAGE DISTANCE: " + current_avgRMS);
	}
	
}
