package com.iagl.view;

import javax.swing.JFrame;

import com.iagl.model.sma.SMA;

public class Window extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 258997989256409910L;

	public Window(SMA sma) {
		this.setTitle("Particles");
		this.setResizable(true);
		this.setContentPane(new ParticlePanel(sma, 640, 640));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void start() {
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
