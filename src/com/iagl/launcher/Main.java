package com.iagl.launcher;

import java.io.IOException;
import java.util.Random;

import com.iagl.model.map.Environment;
import com.iagl.model.sma.SMA;
import com.iagl.model.util.Parameters;
import com.iagl.model.util.Trace;
import com.iagl.view.Window;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Parameters parameters = new Parameters(args[0]);
		Random random;
		if(parameters.getSeed() != -1) {
			random = new Random(parameters.getSeed());
	    } else {
			random = new Random();
		}
		Trace trace = new Trace(parameters.isTrace(), parameters.getPrintLocation());

		Environment env = new Environment(
				parameters.getGridSizeX(), 
				parameters.getGridSizeY(), 
				parameters.getNbParticles(), 
				parameters.isTorus(),
				parameters.getPrintLocation(),
				random,
				trace);
		SMA sma = new SMA(
				env, 
				parameters.getDelay(), 
				parameters.getTicks(),
				parameters.getRefresh(),
				parameters.getPrintLocation(),
				parameters.getScheduling(),
				random,
				trace);
		Window window = new Window(sma, parameters);
		window.start();
		new Thread(sma).start();
	}

}
