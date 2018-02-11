package com.iagl.avatar.launcher;

import java.io.IOException;
import java.util.Random;

import com.iagl.avatar.factory.RandomMazeBuilder;
import com.iagl.avatar.map.Maze;
import com.iagl.avatar.util.GameParameters;
import com.iagl.avatar.view.Window;
import com.iagl.core.sma.SMA;
import com.iagl.core.trace.Trace;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		GameParameters parameters = new GameParameters(args[0]);
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
		
		Maze env = new Maze(
				parameters.getGridSizeX(), 
				parameters.getGridSizeY(), 
				parameters.isTorus(), 
				trace, 
				random, 
				new RandomMazeBuilder(),
				parameters.getNbHunters(),
				parameters.getWallPercent(),
				parameters.getDecideHunter(),
				parameters.getDecidePlayer());
		
		SMA sma = new SMA(
				env, 
				parameters.getDelay(), 
				parameters.getNbTicks(), 
				parameters.getRefresh(), 
				parameters.getScheduling());
		Window window = new Window(sma, env, parameters);
		
		window.start();
		new Thread(sma).start();
		
	}


}
