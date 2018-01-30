package com.iagl.wator.util;

import java.io.IOException;

import com.iagl.core.util.Parameters;

public class OceanParameters extends Parameters {
	
	private int nbSharks;
	
	private int nbFishes;
	
	private int sharkBreedTime;
	
	private int fishBreedTime;
	
	private int sharkStarvationTime;

	public OceanParameters(String filePath) throws IOException {
		super(filePath);
	}
	
	@Override
	protected void loadProperties() {
		super.loadProperties();
		
		try {
			this.nbSharks = Integer.valueOf(this.properties.getProperty("nbSharks"));
		} catch(NumberFormatException e) {
			this.nbSharks = 10;
		}
		
		try {
			this.nbFishes = Integer.valueOf(this.properties.getProperty("nbFishes"));
		} catch(NumberFormatException e) {
			this.nbFishes = 100;
		}
		
		try {
			this.sharkBreedTime = Integer.valueOf(this.properties.getProperty("sharkBreedTime"));
		} catch(NumberFormatException e) {
			this.sharkBreedTime = 5;
		}
		
		try {
			this.fishBreedTime = Integer.valueOf(this.properties.getProperty("fishBreedTime"));
		} catch(NumberFormatException e) {
			this.fishBreedTime = 3;
		}
		
		try {
			this.sharkStarvationTime = Integer.valueOf(this.properties.getProperty("sharkStarvationTime"));
		} catch(NumberFormatException e) {
			this.sharkStarvationTime = 5;
		}
		
	}
	
	public int getNbSharks() {
		return nbSharks;
	}
	
	public int getNbFishes() {
		return nbFishes;
	}
	
	public int getSharkBreedTime() {
		return sharkBreedTime;
	}
	
	public int getFishBreedTime() {
		return fishBreedTime;
	}
	
	public int getSharkStarvationTime() {
		return sharkStarvationTime;
	}

}
