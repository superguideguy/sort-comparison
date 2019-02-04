package com.github.superguideguy.sort.comparison.sync;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GUI implements Runnable, KeyListener {
	
	static JFrame frame;

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			exitExternal();
		}	
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

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
		frame.setVisible(true);
		
	}
	
	public static void update() {
		//Draw array (pre-synchronized)
		//Draw bottom bar (TimeKeeper.t_cumm, Runner.task, TimeKeeper.current_avgRMS)
	}
	
	private static void exitExternal() {
		Runtime.getRuntime().exit(-1);
	}
	

}
