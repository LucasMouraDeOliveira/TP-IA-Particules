package com.iagl.wator.agent;

import java.awt.Color;
import java.util.Random;

import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;
import com.iagl.wator.map.OceanEnvironment;

public class Fish extends AquaticAnimal {

	public Fish(int posX, int posY, Random random, int breedTime) {
		super(posX, posY, random, breedTime);
		this.setColor(Color.GREEN);
	}

	@Override
	public void decide(Environment env) {
		
		//Si l'animal est mort, on finit son tour et il ne réalise aucune action
		if(this.isDeceased()) {
			return;
		}
		
		//L'animal est né pendant un tour antérieur
		this.setNewBorn(false);
		
		//On déplace le poisson
		Cell cell = env.getCells(posX, posY);
		if(this.moveInRandomDirection(env)){
			if(this.breedTick == 0) {
				this.breedTick = this.breedTime;
				((OceanEnvironment) env).addNewbornFish(cell);
			}
		}
	}

}
