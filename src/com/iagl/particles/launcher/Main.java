package com.iagl.particles.launcher;

import java.io.IOException;
import java.util.Random;

import com.iagl.core.map.Environment;
import com.iagl.core.sma.SMA;
import com.iagl.core.trace.Trace;
import com.iagl.particles.map.ParticleEnvironment;
import com.iagl.particles.util.ParticleParameters;
import com.iagl.particles.view.Window;

public class Main {
	
	public static void main(String[] args) throws IOException {
		ParticleParameters parameters = new ParticleParameters(args[0]);
		Random random;
		if(parameters.getSeed() != -1) {
			random = new Random(parameters.getSeed());
	    } else {
			random = new Random();
		}
		Trace trace = null;
		if(parameters.isTrace()) {
			trace = new Trace(parameters.getPrintLocation());
		}

		Environment env = new ParticleEnvironment(
				parameters.getGridSizeX(), 
				parameters.getGridSizeY(), 
				parameters.getNbParticles(), 
				parameters.isTorus(),
				trace,
				random);
		SMA sma = new SMA(
				env, 
				parameters.getDelay(), 
				parameters.getNbTicks(),
				parameters.getRefresh(),
				parameters.getScheduling(),
				random);
		Window window = new Window(sma, parameters);
		window.start();
		new Thread(sma).start();
	}

}
