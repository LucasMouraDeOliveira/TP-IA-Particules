package com.iagl.wator.agent;

import java.util.Random;

import com.iagl.core.agent.Agent;

public abstract class AquaticAnimal extends Agent {

	protected Random random;
	
	protected int breedTime;

	protected int breedTick;
	
	//Si l'animal est mort pendant cette frame
	protected boolean deceased;
	
	public AquaticAnimal(int posX, int posY, Random random, int breedTime) {
		super(posX, posY);
		this.random = random;
		this.breedTick = this.breedTime = breedTime;
		this.deceased = false;
	}
	
	@Override
	public void update() {
		if(this.breedTick > 0) {
			this.breedTick--;
		}
	}
	
	
	public boolean isDeceased() {
		return deceased;
	}
	
	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}
	
}
