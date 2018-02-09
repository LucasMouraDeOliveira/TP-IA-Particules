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
import com.iagl.core.trace.Trace;

public class Maze extends Environment {
	
	private MazeBuilder mazeBuilder;
	
	private NodeUpdater nodeUpdater;
	
	private PlayerFactory playerFactory;
	
	private Player player;
	
	private Node[][] nodes;

	public Maze(int width, int height, boolean torus, Trace trace, Random random, MazeBuilder mazeBuilder, int nbHunters, int wallPercent, int decideHunter, int decidePlayer) {
		super(width, height, torus, trace, random);
		this.mazeBuilder = mazeBuilder;
		this.nodeUpdater = new NodeUpdater();
		this.playerFactory = new PlayerFactory();
		this.initAgents(wallPercent, nbHunters, decideHunter, decidePlayer);
	}

	@Override
	public void update() {
		
	}
	
	private void initAgents(int wallPercent, int nbHunters, int decideHunter, int decidePlayer) {
		this.initMaze(wallPercent);
		this.initPlayers(nbHunters, decideHunter, decidePlayer);
		this.initNodes();
	}

	private void initMaze(int wallPercent) {
		this.mazeBuilder.buildMaze(this.cells, this.random, wallPercent);
	}
	
	private void initNodes() {
		this.nodes = new Node[this.cells.length][this.cells[0].length];
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				this.nodes[i][j] = new Node(this.cells[i][j]);
			}
		}
	}

	private void initPlayers(int nbHunters, int decideHunter, int decidePlayer) {
		this.agents = new ArrayList<Agent>();
		List<Cell> cells = this.getEmptyCells();
		Cell cell;
		Hunter hunter;
		for (int i = 0; i < nbHunters; i++) {
			if(!cells.isEmpty()) {
				cell = cells.get(this.random.nextInt(cells.size()));
				cells.remove(cell);
				hunter = this.playerFactory.createHunter(cell.getX(), cell.getY(), decideHunter);
				cell.setAgent(hunter);
				this.agents.add(hunter);
			}
		}
		Cell playerCell = cells.get(this.random.nextInt(cells.size()));
		Player player = this.playerFactory.createPlayer(playerCell.getX(), playerCell.getY(), decidePlayer);
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
	
	public void updateNodes() {
		this.nodeUpdater.updateNodes(this, this.player);
	}	
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Node[][] getNodes() {
		return nodes;
	}

}
