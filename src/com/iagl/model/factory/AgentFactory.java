package com.iagl.model.factory;

import java.util.Random;

import com.iagl.model.agent.Agent;
import com.iagl.model.agent.BounceParticleAgent;

public class AgentFactory {
	
	private Random random;
	
	public AgentFactory(Random random) {
		this.random = random;
	}
	
	public Agent createAgent(int posX, int posY) {
		
		int direction = randomDirection();
		
		int pasX = (direction/3)-1;
		int pasY = (direction%3)-1;
		
		return new BounceParticleAgent(posX, posY, pasX, pasY);
	}
	
	private int randomDirection() {
		int r = (int)(random.nextDouble()*8);
		return r+ ((r >= 4) ? 1 : 0);
	}

}
