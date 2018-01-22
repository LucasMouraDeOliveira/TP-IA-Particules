package com.iagl.model.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parameters {
	
	private Properties properties;
	
	private int gridSizeX;
	
	private int gridSizeY;
	
	private int boxSize;
	
	private int canvasSizeX;
	
	private int canvasSizeY;
	
	private int delay;
	
	private int nbParticles;
	
	private int nbTicks;
	
	private boolean grid;
	
	private long seed;
	
	private boolean torus;
	
	private int refresh;
	
	public Parameters(String filePath) throws IOException {
		this.properties = new Properties();
		this.properties.load(new FileInputStream(new File(filePath)));
		this.loadProperties();
	}

	private void loadProperties() {
		try {
			this.gridSizeX = Integer.valueOf(this.properties.getProperty("gridSizeX"));
		} catch(NumberFormatException e) {
			this.gridSizeX = 20;
		}
		try {
			this.gridSizeY = Integer.valueOf(this.properties.getProperty("gridSizeY"));
		} catch(NumberFormatException e) {
			this.gridSizeY = 20;
		}
		try {
			this.boxSize = Integer.valueOf(this.properties.getProperty("boxSize"));
		} catch(NumberFormatException e) {
			this.boxSize = 32;
		}
		try {
			this.canvasSizeX = Integer.valueOf(this.properties.getProperty("canvasSizeX"));
		} catch(NumberFormatException e) {
			this.canvasSizeX = 640;
		}
		try {
			this.canvasSizeY = Integer.valueOf(this.properties.getProperty("canvasSizeY"));
		} catch(NumberFormatException e) {
			this.canvasSizeY = 640;
		}
		try {
			this.delay = Integer.valueOf(this.properties.getProperty("delay"));
		} catch(NumberFormatException e) {
			this.delay = 50;
		}
		try {
			this.nbParticles = Integer.valueOf(this.properties.getProperty("nbParticles"));
		} catch(NumberFormatException e) {
			this.nbParticles = 10;
		}
		
		String strGrid = this.properties.getProperty("grid");
		if(strGrid == null || !strGrid.trim().equals("true")) {
			this.grid = false;
		} else {
			this.grid = true;
		}
		
		try {
			this.nbTicks = Integer.valueOf(this.properties.getProperty("nbTicks"));
		} catch(NumberFormatException e) {
			this.nbTicks = 0;
		}
		try {
			this.seed = Long.valueOf(this.properties.getProperty("nbTicks"));
		} catch(NumberFormatException e) {
			this.seed = 0;
		}
		
		String strTorus = this.properties.getProperty("torus");
		if(strTorus == null || !strTorus.trim().equals("true")) {
			this.torus = false;
		} else {
			this.torus = true;
		}
		
		try {
			this.refresh = Integer.valueOf(this.properties.getProperty("refresh"));
		} catch(NumberFormatException e) {
			this.refresh = 0;
		}
	}
	
	public int getGridSizeX() {
		return gridSizeX;
	}
	
	public int getGridSizeY() {
		return gridSizeY;
	}

	public int getBoxSize() {
		return boxSize;
	}

	public int getCanvasSizeX() {
		return canvasSizeX;
	}

	public int getCanvasSizeY() {
		return canvasSizeY;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public int getNbParticles() {
		return nbParticles;
	}

	public boolean isGridDisplayed() {
		return grid;
	}

	public int getTicks() {
		return nbTicks;
	}

	public long getSeed() {
		return seed;
	}

	public boolean isTorus() {
		return torus;
	}
	
	public int getRefresh() {
		return refresh;
	}
	
}
