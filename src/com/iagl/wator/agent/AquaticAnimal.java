package com.iagl.wator.agent;

import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Environment;

public abstract class AquaticAnimal extends Agent {

	protected Random random;
	
	protected int breedTime;

	protected int breedTick;
	
	//Si l'animal vient de naitre pendant cette frame
	protected boolean newBorn;
	
	//Si l'animal est mort pendant cette frame
	protected boolean deceased;
	
	public AquaticAnimal(int posX, int posY, Random random, int breedTime) {
		super(posX, posY);
		this.random = random;
		this.breedTick = this.breedTime = breedTime;
		this.newBorn = true;
		this.deceased = false;
	}
	
	@Override
	public void update() {
		if(this.breedTick > 0) {
			this.breedTick--;
		}
	}
	
	@Override
	public void decide(Environment env) {
		//L'animal est né pendant un tour antérieur
		this.setNewBorn(false);
	}
	
	public boolean isNewBorn() {
		return newBorn;
	}
	
	public void setNewBorn(boolean newBorn) {
		this.newBorn = newBorn;
	}
	
	public boolean isDeceased() {
		return deceased;
	}
	
	public void setDeceased(boolean deceased) {
		this.deceased = deceased;
	}
	
}
