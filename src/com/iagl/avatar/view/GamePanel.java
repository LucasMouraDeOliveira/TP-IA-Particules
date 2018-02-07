package com.iagl.avatar.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.iagl.avatar.map.Maze;
import com.iagl.avatar.map.Node;
import com.iagl.core.sma.SMA;
import com.iagl.core.util.Parameters;

public class GamePanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3447431203347846379L;
	
	private Maze maze;
	
	private Parameters parameters;
	
	private int cs;

	private boolean displayGrid;
	
	public GamePanel(SMA sma, Maze maze, Parameters parameters) {
		sma.addObserver(this);
		this.maze = maze;
		this.parameters = parameters;
		this.displayGrid = parameters.isGridDisplayed();
		this.setFocusable(true);
		this.initGraphicParticleParameters();
	}
	
	public void addPlayerListener() {
		this.requestFocusInWindow();
		this.addKeyListener(this.maze.getPlayer());
	}

	private void initGraphicParticleParameters() {
		this.cs = parameters.getBoxSize();
		this.setPreferredSize(new Dimension(parameters.getGridSizeX()*cs, parameters.getGridSizeY()*cs));
	}
	
	@Override
	public void paintComponent(Graphics g ) {
		
		Node[][] nodes = this.maze.getNodes();
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, nodes.length*cs, nodes[0].length*cs);
		
		g.setColor(Color.black);
		g.drawRect(0, 0, nodes.length*cs, nodes[0].length*cs);
		

		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				
				//Drawing the grid
				if(displayGrid) {
					g.setColor(Color.darkGray);
					g.drawRect(i*cs, j*cs, cs, cs);
				}
				
				//Drawing the walls
				if(!nodes[i][j].getCell().isEmpty()) {
					g.setColor(nodes[i][j].getCell().getAgent().getColor());
					g.fillRect(i*cs, j*cs, cs, cs);
				} else {
					//Drawing the distance
					g.setColor(Color.white);
					g.drawString(nodes[i][j].getWeight()+"", i*cs+4, j*cs+12);
				}
				
			}
		}
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Maze) {
			this.maze = (Maze)arg;
			this.repaint();
		}
	}

}
