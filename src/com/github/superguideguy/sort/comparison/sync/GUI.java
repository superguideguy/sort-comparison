package com.github.superguideguy.sort.comparison.sync;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI implements Runnable, KeyListener {
	
	static JFrame frame;
	static JPanel mainPanel, sidePanel;

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
		frame.setTitle("Comparison of Sorting Methods for Partial Sorting and other Adaptive Sorting Objectives");
		frame.setSize(1000,550);
		frame.setBackground(Color.BLACK);
		frame.setForeground(Color.BLACK);
		
		mainPanel = new JPanel();
		mainPanel.setSize(1000, 500);
		mainPanel.setBackground(Color.BLACK);
		frame.add(mainPanel);
		
		sidePanel = new JPanel();
		sidePanel.setSize(1000, 50);
		sidePanel.setBackground(Color.GRAY);
		frame.add(sidePanel);
		
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
