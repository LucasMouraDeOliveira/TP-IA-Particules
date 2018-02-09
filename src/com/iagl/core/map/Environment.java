package com.iagl.core.map;

import java.util.List;
import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.core.trace.Trace;

public abstract class Environment {
	
	protected Cell[][] cells;
	
	protected List<Agent> agents;
	
	protected boolean torus;

	protected Trace trace;	
	
	protected Random random;

	public Environment(int width, int height, boolean torus, Trace trace, Random random) {
		this.torus = torus;
		this.trace = trace;
		this.random = random;
		this.initCells(width, height);
	}
	
	public abstract void update();	
	
	private void initCells(int width, int height) {
		this.cells = new Cell[width][height];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
	}
	
	public Cell getCells(int x, int y) {
		if(torus) {
			if(x < 0) {
				x = this.cells.length-1;
			} else if(x > this.cells.length-1){
				x = 0;
			}
			
			if(y < 0) {
				y = this.cells[0].length-1;
			} else if(y > this.cells[0].length-1){
				y = 0;
			}	
		} else if(x < 0 || x > this.cells.length-1 || y < 0 || y > this.cells[0].length-1) {
			return null;
		}
		
		return cells[x][y];
	}
	
	@Override
	public String toString() {
		String retour = "";
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				retour += (cells[i][j].isEmpty() ? " " : "x");
			}
			retour += "\n";
		}
		return retour;
	}
	
	public List<Agent> getAgents() {
		return this.agents;
	}
	
	public Cell[][] getCells() {
		return this.cells;
	}
	
	public int getWidth() {
		return this.cells.length;
	}
	
	public int getHeight() {
		return this.cells[0].length;
	}
	
	public Trace getTrace() {
		return trace;
	}
	
}
