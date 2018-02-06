package com.iagl.avatar.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.iagl.avatar.agent.Hunter;
import com.iagl.avatar.agent.Player;
import com.iagl.avatar.factory.MazeBuilder;
import com.iagl.avatar.factory.PlayerFactory;
import com.iagl.core.agent.Agent;
import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;

import todo.Trace;

public class Maze extends Environment {
	
	private MazeBuilder mazeBuilder;
	
	private PlayerFactory playerFactory;
	
	private Player player;

	public Maze(int width, int height, boolean torus, Trace trace, Random random, MazeBuilder mazeBuilder, int nbHunters, int wallPercent) {
		super(width, height, torus, trace, random);
		this.mazeBuilder = mazeBuilder;
		this.playerFactory = new PlayerFactory();
		this.initAgents(wallPercent, nbHunters);
	}

	@Override
	public void update() {
		//nothing
	}
	
	private void initAgents(int wallPercent, int nbHunters) {
		this.initMaze(wallPercent);
		this.initPlayers(nbHunters);
	}

	private void initMaze(int wallPercent) {
		this.mazeBuilder.buildMaze(this.cells, this.random, wallPercent);
	}

	private void initPlayers(int nbHunters) {
		this.agents = new ArrayList<Agent>();
		List<Cell> cells = this.getEmptyCells();
		Cell cell;
		Hunter hunter;
		for (int i = 0; i < nbHunters; i++) {
			if(!cells.isEmpty()) {
				cell = cells.get(this.random.nextInt(cells.size()));
				cells.remove(cell);
				hunter = this.playerFactory.createHunter(cell.getX(), cell.getY());
				cell.setAgent(hunter);
				this.agents.add(hunter);
			}
		}
		Cell playerCell = cells.get(this.random.nextInt(cells.size()));
		Player player = this.playerFactory.createPlayer(playerCell.getX(), playerCell.getY());
		playerCell.setAgent(player);
		this.agents.add(player);
		this.player = player;
	}

	private List<Cell> getEmptyCells() {
		List<Cell> emptyCells = new ArrayList<Cell>();
		Cell cell;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cell = cells[i][j];
				if(cell.isEmpty()) {
					emptyCells.add(cell);
				}
			}
		}
		return emptyCells;
	}
	
	public Player getPlayer() {
		return this.player;
	}

}
