package com.iagl.core.map;

import com.iagl.core.agent.Agent;

public class Cell {
	
	private final int x;
	
	private final int y;
	
	private Agent agent;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public boolean isEmpty() {
		return this.agent == null;
	}

}
