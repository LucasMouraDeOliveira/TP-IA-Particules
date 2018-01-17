package com.iagl.model.factory;

import com.iagl.model.agent.Agent;
import com.iagl.model.agent.BounceParticleAgent;

public class AgentFactory {
	
	public static Agent createAgent(int posX, int posY) {
		
		int direction = randomDirection();
		
		int pasX = (direction/3)-1;
		int pasY = (direction%3)-1;
		
		return new BounceParticleAgent(posX, posY, pasX, pasY);
	}
	
	public static int randomDirection() {
		int random = (int)(Math.random()*8);
		return random+ ((random >= 4) ? 1 : 0);
	}

}
