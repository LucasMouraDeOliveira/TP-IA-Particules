package com.iagl.particles.agent;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Environment;
import com.iagl.particles.map.ParticleEnvironment;

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
		((ParticleEnvironment)env).registerCollision();
	}

}
