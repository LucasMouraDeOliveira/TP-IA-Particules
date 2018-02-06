package com.iagl.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parameters {
	
	protected Properties properties;
	
	protected int gridSizeX;
	
	protected int gridSizeY;
	
	protected int boxSize;
	
	protected int canvasSizeX;
	
	protected int canvasSizeY;
	
	protected int delay;
	
	protected int nbTicks;
	
	protected boolean grid;
	
	protected long seed;
	
	protected boolean torus;
	
	protected int refresh;
	
	protected Scheduling scheduling;
	
	protected boolean trace;

	protected String printLocation;
	
	public Parameters(String filePath) throws IOException {
		this.properties = new Properties();
		this.properties.load(new FileInputStream(new File(filePath)));
		this.loadProperties();
	}
	
	protected void loadProperties() {
		
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
			this.seed = Long.valueOf(this.properties.getProperty("seed"));
		} catch(NumberFormatException e) {
			this.seed = -1;
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
		
		String strTrace = this.properties.getProperty("trace");
		if(strTrace == null || !strTrace.trim().equals("true")) {
			this.trace = false;
		} else {
			this.trace = true;
		}
		
		this.printLocation = this.properties.getProperty("printLocation");
		if(this.printLocation == null || this.printLocation.trim().isEmpty()) {
			this.printLocation = null;
		}
		
		String str = this.properties.getProperty("scheduling");
		switch(str){
			case "aleatoire": 
				this.scheduling = Scheduling.ALEATOIRE;
				break;
			case "equitable": 
				this.scheduling = Scheduling.EQUITABLE;
				break;
			default: 
				this.scheduling = Scheduling.SEQUENTIEL;
				break;
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

	public int getNbTicks() {
		return nbTicks;
	}

	public boolean isGridDisplayed() {
		return grid;
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

	public Scheduling getScheduling() {
		return scheduling;
	}

	public boolean isTrace() {
		return trace;
	}

	public String getPrintLocation() {
		return printLocation;
	}

}
