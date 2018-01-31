package com.iagl.avatar.factory;

import java.util.Random;

import com.iagl.core.map.Cell;

public interface MazeBuilder {
	
	public void buildMaze(Cell[][] cells, Random random, int percent);

}
