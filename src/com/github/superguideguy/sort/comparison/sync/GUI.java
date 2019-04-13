/*********************************************************************
* Copyright (c) 2019 superguideguy
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package com.github.superguideguy.sort.comparison.sync;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI implements Runnable, KeyListener {
	
	static JFrame frame;
	static JPanel mainPanel, sidePanel;
	final static int mpH = 500, spH = 50, width = 1000;

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
		frame.setSize(width, mpH+spH);
		frame.setBackground(Color.BLACK);
		frame.setForeground(Color.BLACK);
		
		mainPanel = new JPanel();
		mainPanel.setSize(width, mpH);
		mainPanel.setBackground(Color.BLACK);
		frame.add(mainPanel);
		
		sidePanel = new JPanel();
		sidePanel.setSize(width, spH);
		sidePanel.setBackground(Color.GRAY);
		frame.add(sidePanel);
		
		frame.setVisible(true);
		update();
	}
	
	public static void update() {
		//Draw array (pre-synchronized)
		Graphics2D g2;
		Graphics g3;
		//Draw bottom bar (TimeKeeper.t_cumm, Runner.task, TimeKeeper.current_avgRMS)
		Image img2 = sidePanel.createVolatileImage(width, spH);
		g2 = (Graphics2D)img2.getGraphics();
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2.drawString(Runner.task.name(), 5+100*(width/300), spH-5);
		g3 = sidePanel.getGraphics();
		g3.drawImage(img2, 0, 0, null);
	}
	
	private static void exitExternal() {
		Runtime.getRuntime().exit(-1);
	}
	

}
