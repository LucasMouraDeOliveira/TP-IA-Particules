package com.iagl.core.sma;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Environment;
import com.iagl.core.util.Scheduling;

public class SMA extends Observable implements Runnable {
	
	private int delay;
	
	private boolean infinite;
		
	private int remainingTicks;
	
	private int currentFrame;
	
	private int refresh;
	
	private Scheduling scheduling;
	
	private Environment env;
	
	public SMA(Environment env, int delay, int ticks, int refresh, Scheduling scheduling) {
		this.env = env;
		this.delay = delay;
		this.remainingTicks = ticks;
		this.currentFrame =0;
		this.refresh = refresh;
		this.infinite = (ticks <= 0);
		this.scheduling = scheduling;
	}


	@Override
	public void run() {
		long updateTime;
		while(shouldContinue()) {
			try {
				updateTime = System.currentTimeMillis();
				this.update();
				updateTime = (System.currentTimeMillis()-updateTime);
				//System.out.println(updateTime);
				Thread.sleep(Math.max(0, delay-updateTime));
			} catch (InterruptedException e) {}
		}
		
		if(this.env.getTrace() != null) {
			this.env.getTrace().close();
		}
		
		System.exit(0);
		
	}
	
	private boolean shouldContinue() {
		this.currentFrame++;
		return infinite || (--this.remainingTicks)>=0;
	}
	
	private void update() {
		this.scheduling();
		this.env.update();
		if(this.currentFrame % this.refresh == 0) {
			this.notifyObservers();
		}
	}
	
	private void scheduling() {
		
		List<Agent> agents = this.env.getAgents();
		Random random = this.env.getRandom();
		
		switch (this.scheduling) {
			case EQUITABLE:
				Collections.shuffle(agents, random);
				for(Agent agent : agents) {
					agent.decide(env);
					agent.update();
				}
				break;
			case ALEATOIRE:
				int index;
				for(int i=0; i<agents.size();i++) {
					index = random.nextInt(agents.size());
					agents.get(index).decide(env);
					agents.get(index).update();
				}
				break;
			default:
				for(Agent agent : agents) {
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
