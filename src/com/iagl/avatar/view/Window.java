package com.iagl.avatar.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.iagl.avatar.map.Maze;
import com.iagl.avatar.util.GameParameters;
import com.iagl.core.sma.SMA;

public class Window extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 258997989256409910L;
	
	private static final int SCROLL_OFFSET = 3;
	
	private GamePanel gamePanel;

	public Window(SMA sma, Maze maze, GameParameters parameters) {
		this.setTitle("Avatar by Lucas Cameron");
		this.setResizable(true);
		this.initContentPane(sma, maze, parameters);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void start() {
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.gamePanel.addPlayerListener();
	}
	
	private void initContentPane(SMA sma, Maze maze, GameParameters parameters) {
		this.gamePanel = new GamePanel(sma, maze, parameters);
		JScrollPane scrollPane = new JScrollPane(this.gamePanel);
		scrollPane.setPreferredSize(new Dimension(parameters.getCanvasSizeX()+SCROLL_OFFSET, parameters.getCanvasSizeY() + SCROLL_OFFSET));
		this.setContentPane(scrollPane);
	}

}