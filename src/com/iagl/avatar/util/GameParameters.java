package com.iagl.avatar.util;

import java.io.IOException;

import com.iagl.core.util.Parameters;

public class GameParameters extends Parameters {
	
	private int nbHunters;
	
	private int decideHunter;
	
	private int decidePlayer;
	
	private int wallPercent;
	

	public GameParameters(String filePath) throws IOException {
		super(filePath);
	}
	
	@Override
	protected void loadProperties() {
		super.loadProperties();
		
		try {
			this.nbHunters = Integer.valueOf(this.properties.getProperty("nbHunters"));
		} catch(NumberFormatException e) {
			this.nbHunters = 4;
		}
		
		try {
			this.decideHunter = Integer.valueOf(this.properties.getProperty("decideHunter"));
		} catch(NumberFormatException e) {
			this.decideHunter = 1;
		}
		
		try {
			this.decidePlayer = Integer.valueOf(this.properties.getProperty("decidePlayer"));
		} catch(NumberFormatException e) {
			this.decidePlayer = 1;
		}
		
		try {
			this.wallPercent = Integer.valueOf(this.properties.getProperty("wallPercent"));
		} catch(NumberFormatException e) {
			this.wallPercent = 10;
		}
		
	}
	
	public int getNbHunters() {
		return nbHunters;
	}
	
	public int getDecideHunter() {
		return decideHunter;
	}
	
	public int getDecidePlayer() {
		return decidePlayer;
	}
	
	public int getWallPercent() {
		return wallPercent;
	}

}
