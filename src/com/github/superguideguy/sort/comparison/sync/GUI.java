package com.github.superguideguy.sort.comparison.sync;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.time.Instant;

import javax.swing.JFrame;

public class GUI implements Runnable, KeyListener {
	
	static JFrame frame;
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
	public void keyTyped(KeyEvent e) {}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setName("Comparison of Sorting Methods for Partial Sorting and other Adaptive Sorting Objectives");
		//frame.setSize(width, height);
		frame.setBackground(Color.BLACK);
		
		/**/ //TODO: More
		while(true) {
			try {
				Thread.sleep(100);
				synchronized (isHeld) {
					t_now = Instant.now();
					if (isHeld) if (!Duration.between(t_pauseStart, t_now).minus(T_HALT).isNegative())
						haltExternal();
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				WatchdogInternal.haltInterrupted();
			}
		}
		/**/
	}
	
	private static void exitExternal() {
		Runtime.getRuntime().exit(-1);
	}
	
	private static void haltExternal() {
		Runtime.getRuntime().halt(-1);
	}

}
