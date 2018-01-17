package com.iagl.model.sma;

import java.util.List;
import java.util.Observable;

import com.iagl.model.agent.Agent;
import com.iagl.model.map.Environment;

public class SMA extends Observable implements Runnable {
	
	private int delay;
	
	private Environment env;
	
	private List<Agent> agents;
	
	public SMA(Environment env, int delay) {
		this.env = env;
		this.delay = delay;
		this.agents = this.env.getAgents();
	}
	
	@Override
	public void run() {
		
		while(true) {
			try {
				Thread.sleep(delay);
				this.update();
			} catch (InterruptedException e) {}
		}
		
	}
	
	private void update() {
		for(Agent agent : this.agents) {
			agent.decide(env);
			agent.update();
		}
		this.notifyObservers();
	}
	
	@Override
	public void notifyObservers() {
		this.setChanged();
		super.notifyObservers(this.env);
	}

}
