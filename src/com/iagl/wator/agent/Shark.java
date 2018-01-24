package com.iagl.wator.agent;

import java.util.Random;

import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;
import com.iagl.wator.map.OcEnvironment;

public class Shark extends AquaticAnimal {

	public Shark(int posX, int posY, Random random, int breedTime) {
		super(posX, posY, random, breedTime);
	}

	@Override
	public void decide(Environment env) {
		Cell cell = env.getCells(posX, posY);
		if(this.moveInRandomDirection(env)){
			((OcEnvironment)env).addShark(cell);
		}
	}
}