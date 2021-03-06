package com.iagl.particles.map;

import java.util.ArrayList;
import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Environment;
import com.iagl.core.trace.Trace;
import com.iagl.particles.factory.ParticleFactory;

public class ParticleEnvironment extends Environment {
	
	protected ParticleFactory particleFactory;
	
	protected int currentCollision;

	public ParticleEnvironment(int width, int height, int nbAgents, boolean torus, Trace trace, Random random) {
		super(width, height, torus, trace, random);
		this.particleFactory = new ParticleFactory(random);
		this.initAgents(nbAgents);
	}
	
	@Override
	public void update() {
		if(this.trace != null) {
			this.trace.nextTick();
			this.trace.writeLine("COLLISION " + this.currentCollision);
		}
		this.currentCollision = 0;
	}
	
	public void registerCollision() {
		this.currentCollision++;
	}

	public void initAgents(int nbAgents) {
		this.agents = new ArrayList<Agent>(nbAgents);
		int nbFreeCells = cells.length*cells[0].length;
		for (int i = 0; i < nbFreeCells; i++) {
			if(this.random.nextDouble() < nbAgents/(double)(nbFreeCells-i)){
				addAgent(i);
				nbAgents--;
			}
		}
	}
	
	private void addAgent(int nbCell){
		int posX = nbCell/this.cells[0].length;
		int posY = nbCell%this.cells[0].length;
		Agent agent = this.particleFactory.createAgent(posX, posY);
		this.cells[posX][posY].setAgent(agent);
		this.agents.add(agent);
	}

}
