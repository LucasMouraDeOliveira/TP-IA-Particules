package com.iagl.launcher;

import java.io.IOException;

import com.iagl.model.map.Environment;
import com.iagl.model.sma.SMA;
import com.iagl.model.util.Parameters;
import com.iagl.view.Window;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Parameters parameters = new Parameters(args[0]);
		Environment env = new Environment(
				parameters.getGridSizeX(), 
				parameters.getGridSizeY(), 
				parameters.getNbParticles(), 
				parameters.getSeed(),
				parameters.isTorus());
		SMA sma = new SMA(
				env, 
				parameters.getDelay(), 
				parameters.getTicks(),
				parameters.getRefresh());
		Window window = new Window(sma, parameters);
		window.start();
		new Thread(sma).start();
	}

}
