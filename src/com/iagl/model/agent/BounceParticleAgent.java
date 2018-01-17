package com.iagl.model.agent;

import java.awt.Color;

import com.iagl.model.map.Cell;
import com.iagl.model.map.Environment;

public class BounceParticleAgent extends Agent {

	public BounceParticleAgent(int posX, int posY, int pasX, int pasY) {
		super(posX, posY, pasX, pasY);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void decide(Environment env) {
		int newPosX = posX+pasX;
		int newPosY = posY+pasY;
		Cell newCell = env.getCells(newPosX, newPosY);
	
		if(newCell == null) {			
			this.bounce(env);
			return;
		}
		
		if(newCell.isEmpty()) {
			this.move(env, newCell);
		} else {
			this.swapVelocity(newCell.getAgent());
			this.setColor(Color.RED);
		}
			
	}
	
	private void bounce(Environment env) {
		
		int modX = 1;
		int modY = 1;
		
		if(this.posX == 0 || this.posX == env.getWidth()-1) {
			modX = -1;
		}
		if(this.posY == 0 || this.posY == env.getHeight()-1) {
			modY = -1;
		}
		
		this.pasX = modX * pasX;
		this.pasY = modY * pasY;
	}
	
	private void swapVelocity(Agent agent) {
		int tmpX = agent.getPasX();
		int tmpY = agent.getPasY();
		agent.setPasX(this.pasX);
		agent.setPasY(this.pasY);
		this.setPasX(tmpX);
		this.setPasY(tmpY);
	}

}
