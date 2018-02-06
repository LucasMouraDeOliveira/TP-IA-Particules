package com.iagl.avatar.agent;
import java.awt.Color;

import com.iagl.core.agent.Agent;
import com.iagl.core.map.Environment;

public class Wall extends Agent {

	public Wall(int posX, int posY) {
		super(posX, posY);
		this.color = new Color(77, 36, 36);
	}

	@Override
	public void update() {
		//nothing
	}

	@Override
	public void decide(Environment env) {
		//nothing
	}

}
