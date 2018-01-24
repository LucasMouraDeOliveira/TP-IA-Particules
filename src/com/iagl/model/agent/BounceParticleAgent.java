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
			this.swapVelocity(newCell.getAgent(), env);
			this.setColor(Color.RED);
		}
			
	}
	
	private void bounce(Environment env) {
	
		if(this.posX == 0 || this.posX == env.getWidth()-1) {
			this.pasX *= -1;
		}
		
		if(this.posY == 0 || this.posY == env.getHeight()-1) {
			this.pasY *= -1;
		}
		this.setDirection(pasX, pasY, env);
	}

}
