package com.iagl.wator.agent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;
import com.iagl.wator.map.OceanEnvironment;

public class Shark extends AquaticAnimal {
	
	private int starvationTick;
	
	private int starvationTime;

	public Shark(int posX, int posY, Random random, int breedTime, int starvationTime) {
		super(posX, posY, random, breedTime);
		this.starvationTick = this.starvationTime = starvationTime;
		this.setColor(Color.RED);
	}
	
	@Override
	public void update() {
		super.update();
		if(this.starvationTick > 0) {
			this.starvationTick--;
		}
	}

	@Override
	public void decide(Environment env) {
		
		if(this.isDeceased()) {
			return;
		}
		
		this.setNewBorn(false);
		
		//If the shark is starving, it dies
		if(this.starvationTick == 0) {
			((OceanEnvironment)env).addDeceasedAnimal(this);
			return;
		}
		
		Cell cell = env.getCells(posX, posY);
		
		//First the shark tries to eat a fish
		Fish fish = this.getNeighborFish(env);
		if(fish != null) {
			this.starvationTick = this.starvationTime;
			Cell fishCell = env.getCells(fish.getPosX(), fish.getPosY());
			((OceanEnvironment)env).addDeceasedAnimal(fish);
			this.move(env, fishCell);
			if(this.breedTick == 0) {
				this.breedTick = this.breedTime;
				((OceanEnvironment)env).addNewbornShark(cell);
			}
			return;
		}
		
		//If it can't, it tries to move
		if(this.moveInRandomDirection(env)){
			if(this.breedTick == 0) {
				this.breedTick = this.breedTime;
				((OceanEnvironment)env).addNewbornShark(cell);
			}
		}
		
	}
	
	private Fish getNeighborFish(Environment environment) {
		List<Cell> cells = getNeighborCellContainingFish(environment);
		if(cells.isEmpty()) {
			return null;
		} else {
			return (Fish) cells.get((int)(Math.random()*cells.size())).getAgent();
		}
	}

	private List<Cell> getNeighborCellContainingFish(Environment environment) {
		List<Cell> fishCells = new ArrayList<Cell>();
		Cell cell;
		for(int i=posX-1;i<=posX+1;i++) {
			for(int j=posY-1;j<=posY+1;j++) {
				if(i != posX || j != posY) {
					cell = environment.getCells(i, j);
					if(cell != null && !cell.isEmpty() && cell.getAgent() instanceof Fish) {
						fishCells.add(cell);
					}
				}
			}
		}
		return fishCells;
	}
}
