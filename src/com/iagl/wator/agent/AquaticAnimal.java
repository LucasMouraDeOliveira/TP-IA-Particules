package com.iagl.wator.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Cell;
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
	
	protected boolean moveInRandomDirection(Environment environment) {
		Cell cell = getEmptyNeighborCell(environment);
		if(cell != null) {
			this.move(environment, cell);
			return true;
		}
		return false;
	}
	
	private Cell getEmptyNeighborCell(Environment environment) {
		List<Cell> cells = getEmptyNeighborCells(environment);
		if(cells.isEmpty()) {
			return null;
		} else {
			return cells.get((int)(Math.random()*cells.size()));
		}
	}

	private List<Cell> getEmptyNeighborCells(Environment environment) {
		List<Cell> emptyCells = new ArrayList<Cell>();
		Cell cell;
		for(int i=posX-1;i<=posX+1;i++) {
			for(int j=posY-1;j<=posY+1;j++) {
				if(i != posX || j != posY) {
					cell = environment.getCells(i, j);
					if(cell != null && cell.isEmpty()) {
						emptyCells.add(cell);
					}
				}
			}
		}
		return emptyCells;
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
