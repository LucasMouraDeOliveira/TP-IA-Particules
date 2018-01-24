package com.iagl.particles.agent;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Environment;

public abstract class Particle extends Agent {
	
	protected int pasX;
	
	protected int pasY;
	
	public Particle(int posX, int posY, int pasX, int pasY) {
		super(posX, posY);
		this.pasX = pasX;
		this.pasY = pasY;
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
	
	public void setDirection(int pasX, int pasY, Environment env){
		this.pasX = pasX;
		this.pasY = pasY;
		env.getTrace().traceCollision(this);
	}
	
	public void swapVelocity(Particle particle, Environment env) {
		int tmpX = particle.getPasX();
		int tmpY = particle.getPasY();
		particle.setDirection(this.pasX,this.pasY, env);
		this.setDirection(tmpX, tmpY, env);
	}

}
