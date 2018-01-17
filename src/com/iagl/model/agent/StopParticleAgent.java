package com.iagl.model.agent;

import com.iagl.model.map.Cell;
import com.iagl.model.map.Environment;

public class StopParticleAgent extends Agent {

	public StopParticleAgent(int posX, int posY, int pasX, int pasY) {
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
