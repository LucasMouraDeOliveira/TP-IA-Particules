package com.iagl.core.sma;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Environment;
import com.iagl.core.util.Scheduling;

import todo.Trace;

public class SMA extends Observable implements Runnable {
	
	private int delay;
	
	private boolean infinite;
		
	private int currentTicks;
	
	private int currentFrame;
	
	private int refresh;
	
	private Scheduling scheduling;
	
	private Environment env;
	
	private List<Agent> agents;

	private Random random;

	private Trace trace;
	
	public SMA(Environment env, int delay, int ticks, int refresh, Scheduling scheduling, Random random, Trace trace) {
		this.env = env;
		this.delay = delay;
		this.currentTicks = ticks;
		this.currentFrame =0;
		this.refresh = refresh;
		this.infinite = (ticks <= 0);
		this.agents = this.env.getAgents();
		this.scheduling = scheduling;
		this.random = random;
		this.trace = trace;
	}


	@Override
	public void run() {
		while(shouldContinue()) {
			try {
				this.update();
				Thread.sleep(delay);
			} catch (InterruptedException e) {}
		}
		
	}
	
	private boolean shouldContinue() {
		this.currentFrame++;
		this.trace.traceTick(currentFrame);
		return infinite || --this.currentTicks>=0;
	}
	
	private void update() {
		this.scheduling(this.agents);
		this.env.update();
		if(this.currentFrame % this.refresh == 0) {
			this.notifyObservers();
		}
	}
	
	private void scheduling(List<Agent> agents) {
		switch (this.scheduling) {
			case EQUITABLE:
				Collections.shuffle(agents, random);
				for(Agent agent : this.agents) {
					agent.decide(env);
					agent.update();
				}
				break;
			case ALEATOIRE:
				int index;
				for(int i=0; i<this.agents.size();i++) {
					index = random.nextInt(this.agents.size());
					agents.get(index).decide(env);
					agents.get(index).update();
				}
				break;
			default:
				for(Agent agent : this.agents) {
					agent.decide(env);
					agent.update();
				}
				break;
			}
	}

	@Override
	public void notifyObservers() {
		this.setChanged();
		super.notifyObservers(this.env);
	}

}
