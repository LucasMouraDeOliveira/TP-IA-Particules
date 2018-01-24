package com.iagl.particles.agent;

import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;

public class StopParticle extends Particle {

	public StopParticle(int posX, int posY, int pasX, int pasY) {
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
			return;
		}
		
		if(newCell.isEmpty()) {
			this.move(env, newCell);
		}
		
	}

}
