package com.iagl.wator.agent;

import java.awt.Color;
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
		this.setColor(Color.PINK);
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
		
		//Si l'animal est mort, on finit son tour et il ne réalise aucune action
		if(this.isDeceased()) {
			return;
		}
		
		this.setColor(Color.RED);
		
		//If the shark is starving, it dies
		if(this.starvationTick == 0) {
			((OceanEnvironment)env).addDeceasedAnimal(this);
			return;
		}
		
		Cell cell = this.act(env);
		
		if(cell != null) {
			if(!cell.isEmpty()) {
				Fish fish = (Fish)cell.getAgent();
				this.starvationTick = this.starvationTime;
				((OceanEnvironment)env).addDeceasedAnimal(fish);
			}
			Cell currentCell = env.getCells(this.posX, this.posY);
			this.move(env, cell);
			if(this.breedTick == 0) {
				this.breedTick = breedTime;
				((OceanEnvironment)env).addNewbornShark(currentCell);
			}
		}
		
	}
	
	private Cell act(Environment env) {
		 int random = this.random.nextInt(9);
		 Cell cell = null, emptyCell = null;
		 int x, y;
		 for(int i = 0; i < 9; i++) {
			 random = (random+1)%9;
			 x = (random)%3+this.posX-1;
			 y = (random)/3+this.posY-1;
			 cell = env.getCells(x, y);
			 if(cell != null) {
				 if(!cell.isEmpty()) {
					 if(cell.getAgent() instanceof Fish) {
						 return cell;
					 }
				 } else {
					 emptyCell = cell;
				 }
			 }
		 }
		 return emptyCell;
	}
	
}
