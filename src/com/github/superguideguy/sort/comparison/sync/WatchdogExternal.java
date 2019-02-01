package com.github.superguideguy.sort.comparison.sync;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.time.Instant;

/**
 * 
 * @author superguideguy
 * @deprecated Will be removed as soon as GUI is implemented, as this does not function. 
 * https://stackoverflow.com/questions/37885816/creating-a-key-listener-without-gui
 *
 */
public class WatchdogExternal implements Runnable, KeyListener {
	
	public static Instant t_pauseStart, t_now;
	public static Boolean isPaused, isHeld;
	
	public static final Duration T_HALT;
	static {
		isPaused = false;
		isHeld = false;
		T_HALT = Duration.ofSeconds(5);
		t_now = Instant.now();
		t_pauseStart = t_now;
	}

	@Override
	public void run() {
		/**/
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
		/**/
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if (arg0.getKeyCode() == KeyEvent.VK_PAUSE) {
			if (arg0.isControlDown() || arg0.isAltDown() || arg0.isShiftDown() || arg0.isAltGraphDown()) 
				haltExternal();
			synchronized (isPaused) {
				isPaused = !isPaused;
				t_pauseStart = Instant.now();
				if (isPaused) System.out.println("PAUSED");
				else System.out.println("RESUMING");
			}
			synchronized (isHeld) {
				isHeld = true;
			}
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			exitExternal();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_PAUSE) {
			synchronized (isHeld) {
				isHeld = false;
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
