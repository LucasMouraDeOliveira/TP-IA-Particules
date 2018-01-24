package com.iagl.core.agent;

import java.awt.Color;

import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;

public abstract class Agent {
	
	protected int posX;
	
	protected int posY;
	
	protected Color color;
		
	public Agent(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.color = Color.gray;
	}
	
	public abstract void update();
	
	public abstract void decide(Environment env);
	
	public void move(Environment env, Cell newCell) {
		newCell.setAgent(this);
		env.getCells(posX, posY).setAgent(null);
		this.posX = newCell.getX();
		this.posY = newCell.getY();
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
}
