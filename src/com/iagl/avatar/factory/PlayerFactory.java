package com.iagl.avatar.factory;

import com.iagl.avatar.agent.Hunter;

public class PlayerFactory {
	
	public Hunter createHunter(int posX, int posY) {
		return new Hunter(posX, posY);
	}

}
