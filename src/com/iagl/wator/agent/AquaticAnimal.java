package com.iagl.wator.agent;

import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;

public abstract class AquaticAnimal extends Agent {

	protected Random random;
	
	protected int breedTime;

	protected int breedTick;
	
	public AquaticAnimal(int posX, int posY, Random random, int breedTime) {
		super(posX, posY);
		this.random = random;
		this.breedTick = this.breedTime = breedTime;
	}
	
	@Override
	public void update() {
		if(this.breedTick > 0) {
			this.breedTick--;
		}
	}
	
	protected boolean moveInRandomDirection(Environment environment) {
		Cell cell = getEmptyNeighborCell();
		if(cell != null) {
			this.move(environment, cell);
			return true;
		}
		return false;
	}
	
	private Cell getEmptyNeighborCell() {
		return null;
	}
	
//	private int randomDirection() {
//		int r = (int)(random.nextDouble()*8);
//		return r+ ((r >= 4) ? 1 : 0);
//	}
	
}
