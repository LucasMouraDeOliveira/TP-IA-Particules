package com.iagl.particles.factory;

import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.particles.agent.RevertParticle;

public class ParticleFactory {
	
	private Random random;
	
	public ParticleFactory(Random random) {
		this.random = random;
	}
	
	public Agent createAgent(int posX, int posY) {
		
		int direction = randomDirection();
		
		int pasX = (direction/3)-1;
		int pasY = (direction%3)-1;
		
//		return new BounceParticle(posX, posY, pasX, pasY);
		return new RevertParticle(posX, posY, pasX, pasY);
//		return new StopParticle(posX, posY, pasX, pasY);
	}
	
	private int randomDirection() {
		int r = (int)(random.nextDouble()*8);
		return r+ ((r >= 4) ? 1 : 0);
	}

}
