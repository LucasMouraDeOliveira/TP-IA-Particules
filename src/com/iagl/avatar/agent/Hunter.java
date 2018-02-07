package com.iagl.avatar.agent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.iagl.avatar.map.Maze;
import com.iagl.avatar.map.Node;
import com.iagl.core.agent.Agent;
import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;

public class Hunter extends Agent {
	
	private int decideHunter;
	
	private int maxDecideHunter;

	public Hunter(int posX, int posY, int decideHunter) {
		super(posX, posY);
		this.maxDecideHunter = this.decideHunter = decideHunter;
		this.setColor(Color.RED);
	}

	@Override
	public void update() {
		if(this.decideHunter > 0) {
			this.decideHunter--;
		}
	}

	@Override
	public void decide(Environment env) {
		
		if(decideHunter > 0) {
			return;
		} else {
			decideHunter = maxDecideHunter;
		}
		
		Cell nextPosition = this.getNextPosition((Maze)env);
		if(nextPosition != null) {
			if(nextPosition.isEmpty()) {
				this.move(env, nextPosition);
			} else if(nextPosition.getAgent() instanceof Player) {
				System.out.println("perdu");
				System.exit(0);
			}
		}
	}
	
	private Cell getNextPosition(Maze maze) {
		Node currentNode = maze.getNodes()[this.posX][this.posY];
		List<Node> neighbors = this.getFreeNeighbors(maze, currentNode);
		if(neighbors.isEmpty()) {
			return null;
		}
		int min = neighbors.get(0).getWeight();
		Node bestNode = neighbors.get(0), tmpNode = null;
		for(int i=1; i< neighbors.size(); i++) {
			tmpNode = neighbors.get(i);
			if(min > tmpNode.getWeight()) {
				min = tmpNode.getWeight();
				bestNode = tmpNode;
			}
		}
		return bestNode.getCell();
	}
	
	private List<Node> getFreeNeighbors(Maze maze, Node node) {
		List<Node> neighbors = new ArrayList<Node>();
		Node north = getFreeNode(maze, node.getCell().getX(), node.getCell().getY()-1);
		Node east = getFreeNode(maze, node.getCell().getX()+1, node.getCell().getY());
		Node south = getFreeNode(maze, node.getCell().getX(), node.getCell().getY()+1);
		Node west = getFreeNode(maze, node.getCell().getX()-1, node.getCell().getY());
		if(north != null && (north.getCell().isEmpty() || north.getCell().getAgent() instanceof Player)) {
			neighbors.add(north);
		}
		if(east != null && (east.getCell().isEmpty() || east.getCell().getAgent() instanceof Player)) {
			neighbors.add(east);
		}
		if(south != null && (south.getCell().isEmpty() || south.getCell().getAgent() instanceof Player)) {
			neighbors.add(south);
		}
		if(west != null && (west.getCell().isEmpty() || west.getCell().getAgent() instanceof Player)) {
			neighbors.add(west);
		}
		return neighbors;
	}
	
	private Node getFreeNode(Maze maze, int x, int y) {
		Cell nodeCell = maze.getCells(x, y);
		if(nodeCell != null) {
			return maze.getNodes()[nodeCell.getX()][nodeCell.getY()];
		}
		return null;
	}

}
