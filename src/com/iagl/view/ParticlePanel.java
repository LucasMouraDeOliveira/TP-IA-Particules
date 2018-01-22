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
import com.iagl.model.util.Parameters;

public class ParticlePanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2493055910558929717L;
	
	private int cs;
	
	private SMA sma;
	
	private Environment environment;
	
	private Parameters parameters;
	
	private boolean displayGrid;
	
	public ParticlePanel(SMA sma, Parameters parameters) {
		this.sma = sma;
		this.parameters = parameters;
		this.sma.addObserver(this);
		this.displayGrid = parameters.isGridDisplayed();
		this.requestFocus();
		this.initGraphicParameters();
	}

	private void initGraphicParameters() {
		this.cs = parameters.getBoxSize();
		this.setPreferredSize(new Dimension(parameters.getGridSizeX()*cs, parameters.getGridSizeY()*cs));
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(environment == null)
			return;
		
		Cell[][] cells = this.environment.getCells();
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if(displayGrid) {
					g.setColor(Color.darkGray);
					g.drawRect(i*cs, j*cs, cs, cs);
				}
				if(!cells[i][j].isEmpty()) {
					g.setColor(cells[i][j].getAgent().getColor());
					g.fillRect(i*cs+1, j*cs+1, cs-1, cs-1);
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
