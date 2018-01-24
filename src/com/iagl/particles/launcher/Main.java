package com.iagl.particles.launcher;

import java.io.IOException;
import java.util.Random;

import com.iagl.core.map.Environment;
import com.iagl.core.sma.SMA;
import com.iagl.particles.map.ParticleEnvironment;
import com.iagl.particles.util.ParticleParameters;
import com.iagl.particles.view.Window;

import todo.Trace;

public class Main {
	
	public static void main(String[] args) throws IOException {
		ParticleParameters parameters = new ParticleParameters(args[0]);
		Random random;
		if(parameters.getSeed() != -1) {
			random = new Random(parameters.getSeed());
	    } else {
			random = new Random();
		}
		Trace trace = new Trace(parameters.isTrace(), parameters.getPrintLocation());

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
				random,
				trace);
		Window window = new Window(sma, parameters);
		window.start();
		new Thread(sma).start();
	}

}
