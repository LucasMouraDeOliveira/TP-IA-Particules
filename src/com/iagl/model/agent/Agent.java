package com.iagl.model.agent;

import java.awt.Color;

import com.iagl.model.map.Cell;
import com.iagl.model.map.Environment;

public abstract class Agent {
	
	protected int posX;
	
	protected int posY;
	
	protected int pasX;
	
	protected int pasY;
	
	protected Color color;
		
	public Agent(int posX, int posY, int pasX, int pasY) {
		this.posX = posX;
		this.posY = posY;
		this.pasX = pasX;
		this.pasY = pasY;
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

	public int getPasX() {
		return pasX;
	}

	public void setPasX(int pasX) {
		this.pasX = pasX;
	}

	public int getPasY() {
		return pasY;
	}

	public void setPasY(int pasY) {
		this.pasY = pasY;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setDirection(int pasX, int pasY, Environment env){
		this.pasX = pasX;
		this.pasY = pasY;
		env.getTrace().traceCollision(this);
	}
	
	public void swapVelocity(Agent agent, Environment env) {
		int tmpX = agent.getPasX();
		int tmpY = agent.getPasY();
		agent.setDirection(this.pasX,this.pasY, env);
		this.setDirection(tmpX, tmpY, env);
	}

}
