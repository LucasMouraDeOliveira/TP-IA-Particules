package com.iagl.wator.map;

import java.util.ArrayList;
import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;
import com.iagl.wator.agent.Fish;
import com.iagl.wator.agent.Shark;
import com.iagl.wator.factory.CroustibatFactory;

import todo.Trace;

public class OcEnvironment extends Environment {
	
	protected CroustibatFactory croustibatFactory;

	private Random random;
	
	public static final int BREED_TIME_FISH = 3;

	public OcEnvironment(int width, int height, boolean torus, Trace trace, Random random, int nbSharks, int nbFishs) {
		super(width, height, torus, trace);
		this.random = random;
		this.croustibatFactory = new CroustibatFactory(random);
		this.initAgents(nbSharks, nbFishs);
	}
	
	public void initAgents(int nbSharks, int nbFishs) {
		this.agents = new ArrayList<Agent>(nbSharks+nbFishs);
		int nbFreeCells = cells.length*cells[0].length;
		for (int i = 0; i < nbFreeCells; i++) {
			if(this.random.nextDouble() < nbFishs/(double)(nbFreeCells-i)){
				addFish(i);
				nbFishs--;
			} else if(this.random.nextDouble() < nbSharks/(double)(nbFreeCells-i)){
				addShark(i);
				nbSharks--;
			}
		}
	}
	
	private void addFish(int nbCell){
		int posX = nbCell/this.cells[0].length;
		int posY = nbCell%this.cells[0].length;
		this.addFish(this.cells[posX][posY]);
	}
	
	private void addShark(int nbCell){
		int posX = nbCell/this.cells[0].length;
		int posY = nbCell%this.cells[0].length;
		this.addShark(this.cells[posX][posY]);
	}
	
	public void addFish(Cell cell){
		Fish fish = this.croustibatFactory.createFish(cell.getX(), cell.getY(), BREED_TIME_FISH);
		cell.setAgent(fish);
		agents.add(fish);
	}
	
	public void addShark(Cell cell){
		Shark shark = this.croustibatFactory.createShark(cell.getX(), cell.getY(), BREED_TIME_FISH);
		cell.setAgent(shark);
		agents.add(shark);
	}

}	
