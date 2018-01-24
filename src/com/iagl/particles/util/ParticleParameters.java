package com.iagl.particles.util;

import java.io.IOException;

import com.iagl.core.util.Parameters;

public class ParticleParameters extends Parameters{
	
	private int nbParticles;
	
	public ParticleParameters(String filePath) throws IOException {
		super(filePath);
	}
	
	@Override
	public void loadProperties() {
		
		super.loadProperties();
		
		try {
			this.nbParticles = Integer.valueOf(this.properties.getProperty("nbParticles"));
		} catch(NumberFormatException e) {
			this.nbParticles = 10;
		}
		
	}
	
	public int getNbParticles() {
		return nbParticles;
	}

}
