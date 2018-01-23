package com.iagl.model.sma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import com.iagl.model.agent.Agent;
import com.iagl.model.map.Environment;

public class SMA extends Observable implements Runnable {
	
	private int delay;
	
	private boolean infinite;
	
	private boolean trace;
	
	private int currentTicks;
	
	private int currentFrame;
	
	private int refresh;
	
	private Environment env;
	
	private List<Agent> agents;
	
	public SMA(Environment env, int delay, int ticks, int refresh, boolean trace, String printLocation) {
		this.env = env;
		this.delay = delay;
		this.currentTicks = ticks;
		this.currentFrame =0;
		this.refresh = refresh;
		this.infinite = (ticks <= 0);
		this.trace = trace;
		this.agents = this.env.getAgents();
		if(this.trace) {
			this.setSystemPrint(printLocation);
		}
	}
	
	private void setSystemPrint(String printLocation) {
		if(printLocation != null) {
			try {
				System.setOut(new PrintStream(new FileOutputStream(new File(printLocation))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void run() {
		while(shouldContinue()) {
			try {
				Thread.sleep(delay);
				this.update();
			} catch (InterruptedException e) {}
		}
		
	}
	
	private boolean shouldContinue() {
		this.currentFrame++;
		return infinite || --this.currentTicks>=0;
	}
	
	private void update() {
		for(Agent agent : this.agents) {
			agent.decide(env);
			agent.update();
		}
		if(this.currentFrame % this.refresh == 0) {
			this.notifyObservers();
		}
		this.updateCollision();
	}
	
	private void updateCollision() {
		if(this.trace) {
			Iterator<Agent> it = this.env.getCollidingAgents();
			Agent agent;
			while(it.hasNext()) {
				agent = it.next();
				System.out.println("Agent;"+agent.getPosX()+";"+agent.getPosY()+";"+agent.getPasX()+";"+agent.getPasY()+";");
			}
			System.out.println("Tick;"+this.currentFrame+";");
		}
		this.env.clearCollidingAgents();
	}

	@Override
	public void notifyObservers() {
		this.setChanged();
		super.notifyObservers(this.env);
	}

}
