package com.iagl.avatar.factory;

import com.iagl.avatar.agent.Hunter;
import com.iagl.avatar.agent.Player;

public class PlayerFactory {
	
	public Hunter createHunter(int posX, int posY, int decideHunter) {
		return new Hunter(posX, posY, decideHunter);
	}

	public Player createPlayer(int posX, int posY, int decidePlayer) {
		return new Player(posX, posY, decidePlayer);
	}

}
