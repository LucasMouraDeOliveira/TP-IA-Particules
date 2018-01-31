package com.iagl.avatar.factory;

import java.util.Random;

import com.iagl.avatar.agent.Wall;
import com.iagl.core.map.Cell;

public class RandomMazeBuilder implements MazeBuilder {

	@Override
	public void buildMaze(Cell[][] cells, Random random, int percent) {
		int nbFreeCells = cells.length*cells[0].length;
		int nbWalls = nbFreeCells*percent/100;
		int posX, posY;
		for (int i = 0; i < nbFreeCells; i++) {
			if(random.nextDouble() < nbWalls/(double)(nbFreeCells-i)){
				posX = i/cells[0].length;
				posY = i%cells[0].length;
				cells[posX][posY].setAgent(new Wall(posX, posY));
				nbWalls--;
			}
		}
	}

}
