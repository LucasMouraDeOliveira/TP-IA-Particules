package com.iagl.avatar.util;

import java.io.IOException;

import com.iagl.core.util.Parameters;

public class GameParameters extends Parameters {
	
	private int nbHunters;

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
		
	}
	
	public int getNbHunters() {
		return nbHunters;
	}

}
