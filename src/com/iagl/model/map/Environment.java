package com.iagl.model.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.iagl.model.agent.Agent;
import com.iagl.model.factory.AgentFactory;

public class Environment {
	
	private Cell[][] cells;
	
	private List<Agent> agents;
	
	private Random random;
	
	private AgentFactory agentFactory;
	
	private boolean torus;
	
	public Environment(int width, int height, int nbAgents, long seed, boolean torus) {
		if(seed != -1) {
			this.random = new Random(seed);
		} else {
			this.random = new Random();
		}
		this.torus = torus;
		this.agentFactory = new AgentFactory(this.random);
		this.initCells(width, height);
		this.initAgents(nbAgents);
	}
	
	private void initCells(int width, int height) {
		this.cells = new Cell[width][height];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
	}
	
	private void initAgents(int nbAgents) {
		this.agents = new ArrayList<Agent>(nbAgents);
		int nbFreeCells = cells.length*cells[0].length;
		for (int i = 0; i < nbFreeCells; i++) {
			if(this.random.nextDouble() < nbAgents/(double)(nbFreeCells-i)){
				addAgent(i);
				nbAgents--;
			}
		}
	}
	
	private void addAgent(int nbCell){
		int posX = nbCell/this.cells[0].length;
		int posY = nbCell%this.cells[0].length;
		Agent agent = this.agentFactory.createAgent(posX, posY);
		this.cells[posX][posY].setAgent(agent);
		this.agents.add(agent);
	}
	
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
	
	public Cell[][] getCells() {
		return this.cells;
	}
	
	public int getHeight() {
		return this.cells[0].length;
	}
	
	public int getWidth() {
		return this.cells.length;
	}
	
}
