package com.iagl.wator.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;
import com.iagl.wator.agent.AquaticAnimal;
import com.iagl.wator.agent.Fish;
import com.iagl.wator.agent.Shark;
import com.iagl.wator.factory.AquaticAnimalFactory;

import todo.Trace;

public class OceanEnvironment extends Environment {
	
	protected AquaticAnimalFactory aquaticAnimalFactory;
	
	private List<Agent> newborn;
	
	private List<Agent> deceased;
	
	private int fishBreedTime;
	
	private int sharkBreedTime;
	
	private int sharkStarvationTime;

	public OceanEnvironment(int width, int height, boolean torus, Trace trace, Random random, int nbSharks, int nbFishs, int fishBreedTime, int sharkBreedTime, int sharkStarvationTime) {
		super(width, height, torus, trace, random);
		this.fishBreedTime = fishBreedTime;
		this.sharkBreedTime = sharkBreedTime;
		this.sharkStarvationTime = sharkStarvationTime;
		this.aquaticAnimalFactory = new AquaticAnimalFactory(random);
		this.newborn = new ArrayList<Agent>();
		this.deceased = new ArrayList<Agent>();
		this.initAgents(nbSharks, nbFishs);
	}
	
	@Override
	public void update() {
		this.agents.addAll(this.newborn);
		this.agents.removeAll(this.deceased);
		this.newborn.clear();
		this.deceased.clear();
	}
	
	public void initAgents(int nbSharks, int nbFishs) {
		int nbAgents = nbSharks+nbFishs;
		this.agents = new ArrayList<Agent>(nbAgents);
		int nbFreeCells = cells.length*cells[0].length;
		Cell cell;
		for (int i = 0; i < nbFreeCells; i++) {
			if(this.random.nextDouble() < nbAgents/(double)(nbFreeCells-i)){
				cell = getCellAtIndex(i);
				if(this.random.nextDouble() < nbFishs/nbAgents) {
					addFish(cell, this.agents);
					nbFishs--;
				} else {
					addShark(cell, this.agents);
				}
				nbAgents--;
			}
		}
	}
	
	private Cell getCellAtIndex(int i) {
		int posX = i/this.cells[0].length;
		int posY = i%this.cells[0].length;
		return this.cells[posX][posY];
	}
	
	public void addFish(Cell cell, List<Agent> listToAdd){
		Fish fish = this.aquaticAnimalFactory.createFish(cell.getX(), cell.getY(), this.fishBreedTime);
		if(cell.isEmpty()) {
			cell.setAgent(fish);
			listToAdd.add(fish);
		} else {
			throw new RuntimeException("You can't add a fish here " + cell.getX() + " / " + cell.getY());
		}
	}
	
	public void addShark(Cell cell, List<Agent> listToAdd){
		Shark shark = this.aquaticAnimalFactory.createShark(cell.getX(), cell.getY(), this.sharkBreedTime, this.sharkStarvationTime);
		if(cell.isEmpty()) {
			cell.setAgent(shark);
			listToAdd.add(shark);
		} else {
			throw new RuntimeException("You can't add a shark here " + cell.getX() + " / " + cell.getY());
		}
	}
	
	public void addNewbornFish(Cell cell) {
		this.addFish(cell, this.newborn);
	}
	
	public void addNewbornShark(Cell cell) {
		this.addShark(cell, this.newborn);
	}

	public void addDeceasedAnimal(AquaticAnimal animal) {
		Cell cell = this.getCells(animal.getPosX(), animal.getPosY());
		cell.setAgent(null);
		this.deceased.add(animal);
		animal.setDeceased(true);
	}
	
}	
