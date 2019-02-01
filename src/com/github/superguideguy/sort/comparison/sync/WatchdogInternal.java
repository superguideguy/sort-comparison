package com.github.superguideguy.sort.comparison.sync;

import java.time.Duration;
import java.time.Instant;

public class WatchdogInternal implements Runnable {
	
	public static Instant t_lastSortKick, t_lastGUIKick, t_now;
	public static final Duration T_EXIT, T_HALT;
	static {
		T_EXIT = Duration.ofSeconds( 5);
		T_HALT = Duration.ofSeconds(15);
		t_now = Instant.now();
		t_lastSortKick = t_now;
		t_lastGUIKick = t_now;
	}

	
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
				synchronized (t_lastSortKick) {
					t_now = Instant.now();
					if (!Duration.between(t_lastSortKick, t_now).minus(T_HALT).isNegative())
						haltSort();
					t_now = Instant.now();
					if (!Duration.between(t_lastSortKick, t_now).minus(T_EXIT).isNegative())
						exitSort();
				}
				synchronized (t_lastGUIKick) {
					t_now = Instant.now();
					if (!Duration.between(t_lastGUIKick, t_now).minus(T_HALT).isNegative())
						haltGUI();
					if (!Duration.between(t_lastGUIKick, t_now).minus(T_EXIT).isNegative())
						exitGUI();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				WatchdogInternal.haltInterrupted();
			}
		}
		
	}
	
	private static void haltSort() {
		Runtime.getRuntime().halt(+1);
	}
	private static void exitSort() {
		Runtime.getRuntime().exit(+1);
	}
	
	private static void haltGUI() {
		Runtime.getRuntime().halt(+2);
	}
	private static void exitGUI() {
		Runtime.getRuntime().exit(+2);
	}
	
	protected static void haltInterrupted() {
		Runtime.getRuntime().halt(0);
	}

}