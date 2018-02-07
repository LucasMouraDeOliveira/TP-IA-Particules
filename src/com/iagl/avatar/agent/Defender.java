package com.iagl.avatar.agent;

import java.awt.Color;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Environment;

public class Defender extends Agent {
	
	private int lifespan;
	
	private boolean alive;

	public Defender(int posX, int posY, int lifespan) {
		super(posX, posY);
		this.color = Color.GREEN;
	}

	@Override
	public void update() {
		if(this.lifespan <= 0) {
			this.alive = false;
		} else {
			this.lifespan--;
		}
	}

	@Override
	public void decide(Environment env) {
		// TODO Auto-generated method stub

	}
	
	public boolean isAlive() {
		return alive;
	}

}
