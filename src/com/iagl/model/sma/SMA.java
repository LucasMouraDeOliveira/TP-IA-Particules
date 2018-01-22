package com.iagl.model.sma;

import java.util.List;
import java.util.Observable;

import com.iagl.model.agent.Agent;
import com.iagl.model.map.Environment;

public class SMA extends Observable implements Runnable {
	
	private int delay;
	
	private boolean infinite;
	
	private int currentTicks;
	
	private int currentFrame;
	
	private int refresh;
	
	private Environment env;
	
	private List<Agent> agents;
	
	public SMA(Environment env, int delay, int ticks, int refresh) {
		this.env = env;
		this.delay = delay;
		this.currentTicks = ticks;
		this.currentFrame =0;
		this.refresh = refresh;
		this.infinite = (ticks <= 0);
		this.agents = this.env.getAgents();
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
	}
	
	@Override
	public void notifyObservers() {
		this.setChanged();
		super.notifyObservers(this.env);
	}

}
