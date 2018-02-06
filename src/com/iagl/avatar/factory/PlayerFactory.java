package com.iagl.avatar.factory;

import com.iagl.avatar.agent.Hunter;
import com.iagl.avatar.agent.Player;

public class PlayerFactory {
	
	public Hunter createHunter(int posX, int posY) {
		return new Hunter(posX, posY);
	}

	public Player createPlayer(int posX, int posY) {
		return new Player(posX, posY);
	}

}
