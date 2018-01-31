package com.iagl.wator.agent;

import java.awt.Color;
import java.util.Random;

import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;
import com.iagl.wator.map.OceanEnvironment;

public class Fish extends AquaticAnimal {

	public Fish(int posX, int posY, Random random, int breedTime) {
		super(posX, posY, random, breedTime);
		this.setColor(Color.YELLOW);
	}

	@Override
	public void decide(Environment env) {
		
		//Si l'animal est mort, on finit son tour et il ne r�alise aucune action
		if(this.isDeceased()) {
			return;
		}
		
		this.setColor(Color.GREEN);
		
		this.act(env);
		
	}
	
	private void act(Environment env) {
		 int random = this.random.nextInt(9);
		 Cell cell;
		 int x, y;
		 for(int i = 0; i < 9; i++) {
			 random = (random+1)%9;
			 x = (random)/3+this.posX-1;
			 y = (random)%3+this.posY-1;
			 cell = env.getCells(x, y);
			 if(cell != null && cell.isEmpty()) {
				 Cell currentCell = env.getCells(this.posX, this.posY);
				 this.move(env, cell);
				 if(this.breedTick == 0) {
					this.breedTick = this.breedTime;
					((OceanEnvironment) env).addNewbornFish(currentCell);
				}
				return;
			 }
		 }
	}

}
