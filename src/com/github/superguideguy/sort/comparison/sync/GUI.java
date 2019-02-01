package com.github.superguideguy.sort.comparison.sync;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GUI implements Runnable, KeyListener {
	
	static JFrame frame;

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setName("Comparison of Sorting Methods for Partial Sorting and other Adaptive Sorting Objectives");
		//frame.setSize(width, height);
		frame.setBackground(Color.BLACK);
	}

}
