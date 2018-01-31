package com.iagl.avatar.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.iagl.core.sma.SMA;
import com.iagl.core.util.Parameters;
import com.iagl.wator.util.OceanParameters;

public class Window extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 258997989256409910L;
	
	private static final int SCROLL_OFFSET = 3;

	public Window(SMA sma, OceanParameters parameters) {
		this.setTitle("Avatar by Lucas Cameron");
		this.setResizable(true);
		this.initContentPane(sma, parameters);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void start() {
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void initContentPane(SMA sma, Parameters parameters) {
		GamePanel gamePanel = new GamePanel(sma, parameters);
		JScrollPane scrollPane = new JScrollPane(gamePanel);
		scrollPane.setPreferredSize(new Dimension(parameters.getCanvasSizeX()+SCROLL_OFFSET, parameters.getCanvasSizeY() + SCROLL_OFFSET));
		this.setContentPane(scrollPane);
	}

}