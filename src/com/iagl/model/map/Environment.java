package com.iagl.model.map;

import java.util.ArrayList;
import java.util.List;

import com.iagl.model.agent.Agent;
import com.iagl.model.factory.AgentFactory;

public class Environment {
	
	private Cell[][] cells;
	
	private List<Agent> agents;
	
	public Environment(int width, int height, int nbAgents) {
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
			if(Math.random() < nbAgents/(double)(nbFreeCells-i)){
				addAgent(i);
				nbAgents--;
			}
		}
	}
	
	private void addAgent(int nbCell){
		int posX = nbCell/this.cells.length;
		int posY = nbCell%this.cells.length;
		Agent agent = AgentFactory.createAgent(posX, posY);
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
	
	public static void main(String[] args) {
		Environment env = new Environment(100, 100, 5000);
		System.out.println(env.toString());
		System.out.println(env.agents.size());
	}
	
	public List<Agent> getAgents() {
		return this.agents;
	}

	public Cell getCells(int x, int y) {
		if(x < 0 || x > this.cells.length-1 || y < 0 || y > this.cells[0].length-1)
			return null;
		return cells[x][y];
	}
	
	public Cell[][] getCells() {
		return this.cells;
	}
	
	public int getHeight() {
		return this.cells.length;
	}
	
	public int getWidth() {
		return this.cells[0].length;
	}
	
}
