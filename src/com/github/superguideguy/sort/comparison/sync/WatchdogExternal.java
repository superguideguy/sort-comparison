package com.github.superguideguy.sort.comparison.sync;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.time.Instant;

/**
 * 
 * @author superguideguy
 *
 */
public class WatchdogExternal implements Runnable, KeyListener {
	
	public static Instant t_pauseStart, t_now;
	public static Boolean isPaused;
	
	public static final Duration T_HALT;
	static {
		T_HALT = Duration.ofSeconds(5);
		t_now = Instant.now();
		t_pauseStart = t_now;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
				synchronized (isPaused) {
					t_now = Instant.now();
					if (isPaused) if (!Duration.between(t_pauseStart, t_now).minus(T_HALT).isNegative())
						haltExternal();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				WatchdogInternal.haltInterrupted();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if (arg0.getKeyCode() == KeyEvent.VK_PAUSE) {
			if (arg0.isControlDown() || arg0.isAltDown() || arg0.isShiftDown() || arg0.isAltGraphDown()) 
				haltExternal();
			synchronized (isPaused) {
				isPaused = true;
				t_pauseStart = Instant.now();
			}
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			exitExternal();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_PAUSE) {
			synchronized (isPaused) {
				isPaused = false;
				t_pauseStart = Instant.now();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {	}
	
	private static void exitExternal() {
		Runtime.getRuntime().exit(-1);
	}
	
	private static void haltExternal() {
		Runtime.getRuntime().halt(-1);
	}

}
