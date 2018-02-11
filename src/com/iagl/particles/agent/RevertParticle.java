package com.iagl.particles.agent;

import java.awt.Color;

import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;

public class RevertParticle extends Particle {

	public RevertParticle(int posX, int posY, int pasX, int pasY) {
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
			this.swapVelocity(env);
			this.setColor(Color.RED);
		}
			
	}
	
	public void swapVelocity(Environment env) {
		this.setDirection(-1*this.pasX, -1*this.pasY, env);
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
