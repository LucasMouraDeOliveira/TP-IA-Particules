package com.iagl.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.iagl.model.map.Cell;
import com.iagl.model.map.Environment;
import com.iagl.model.sma.SMA;

public class ParticlePanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2493055910558929717L;
	
	private static int CS = 8;
	
	private SMA sma;
	
	private Environment environment;
	
	private boolean displayGrid = false;
	
	public ParticlePanel(SMA sma, int width, int height) {
		this.sma = sma;
		this.sma.addObserver(this);
		this.setPreferredSize(new Dimension(width, height));
		this.requestFocus();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(environment == null)
			return;
		
		Cell[][] cells = this.environment.getCells();
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(displayGrid) {
					g.setColor(Color.black);
					g.drawRect(i*CS, j*CS, CS, CS);
				}
				if(!cells[i][j].isEmpty()) {
					g.setColor(cells[i][j].getAgent().getColor());
					g.fillRect(i*CS+1, j*CS+1, CS-1, CS-1);
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Environment) {
			this.environment = (Environment)arg;
			this.repaint();
		}
	}
	
}
