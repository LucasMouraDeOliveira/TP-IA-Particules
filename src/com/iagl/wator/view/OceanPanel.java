package com.iagl.wator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Cell;
import com.iagl.core.sma.SMA;
import com.iagl.wator.agent.Fish;
import com.iagl.wator.agent.Shark;
import com.iagl.wator.map.OceanEnvironment;
import com.iagl.wator.util.OceanParameters;

public class OceanPanel extends JPanel implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2955655974503014996L;
	
	private int cs;
	
	private OceanEnvironment environment;
	
	private OceanParameters parameters;
	
	private boolean displayGrid;

	public OceanPanel(SMA sma, OceanParameters parameters) {
		sma.addObserver(this);
		this.parameters = parameters;
		this.displayGrid = parameters.isGridDisplayed();
		this.requestFocus();
		this.initGraphicParticleParameters();
	}

	private void initGraphicParticleParameters() {
		this.cs = parameters.getBoxSize();
		this.setPreferredSize(new Dimension(parameters.getGridSizeX()*cs, parameters.getGridSizeY()*cs));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if(this.environment == null) 
			return;
		
		Cell[][] cells = this.environment.getCells();
		
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, cells.length*cs, cells[0].length*cs);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, cells.length*cs, cells[0].length*cs);
		
		Agent agent;
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				//Drawing the sea
				g.setColor(Color.blue);
				g.fillRect(i*cs, j*cs, cs, cs);
				
				//Drawing the grid
				if(displayGrid) {
					g.setColor(Color.darkGray);
					g.drawRect(i*cs, j*cs, cs, cs);
				}
				
				//Drawing the fish/shark
				if(!cells[i][j].isEmpty()) {
					agent = cells[i][j].getAgent();
					if(agent instanceof Shark) {
						g.setColor(((Shark)agent).isNewBorn() ? Color.PINK : Color.RED);
					} else if(agent instanceof Fish) {
						g.setColor(((Fish)agent).isNewBorn() ? Color.YELLOW : Color.GREEN);
					}
					g.fillRect(i*cs, j*cs, cs, cs);
				}
			}
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof OceanEnvironment) {
			this.environment = (OceanEnvironment)arg;
			this.repaint();
		}
	}

}
