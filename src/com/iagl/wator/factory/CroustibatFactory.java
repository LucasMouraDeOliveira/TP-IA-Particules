package com.iagl.wator.factory;

import java.util.Random;

import com.iagl.wator.agent.Fish;
import com.iagl.wator.agent.Shark;

public class CroustibatFactory {
	
	protected Random random;
	
	public CroustibatFactory(Random random) {
		this.random = random;
	}

	public Fish createFish(int x, int y, int breedTime) {
		return new Fish(x, y, random, breedTime);
	}
	
	public Shark createShark(int x, int y, int breedTime) {
		return new Shark(x, y, random, breedTime);
	}

}
