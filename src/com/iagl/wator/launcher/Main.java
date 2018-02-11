package com.iagl.wator.launcher;

import java.io.IOException;
import java.util.Random;

import com.iagl.core.sma.SMA;
import com.iagl.core.trace.Trace;
import com.iagl.wator.map.OceanEnvironment;
import com.iagl.wator.util.OceanParameters;
import com.iagl.wator.view.Window;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		OceanParameters parameters = new OceanParameters(args[0]);
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
		
		OceanEnvironment env = new OceanEnvironment(
				parameters.getGridSizeX(), 
				parameters.getGridSizeY(), 
				parameters.isTorus(), 
				trace, 
				random, 
				parameters.getNbSharks(), 
				parameters.getNbFishes(),
				parameters.getFishBreedTime(),
				parameters.getSharkBreedTime(),
				parameters.getSharkStarvationTime());
		
		SMA sma = new SMA(
				env, 
				parameters.getDelay(), 
				parameters.getNbTicks(), 
				parameters.getRefresh(), 
				parameters.getScheduling());
		Window window = new Window(sma, parameters);
		
		window.start();
		new Thread(sma).start();
		
	}

}
