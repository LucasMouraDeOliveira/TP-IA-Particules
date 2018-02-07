package com.iagl.avatar.map;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import com.iagl.avatar.agent.Player;
import com.iagl.core.map.Cell;

public class NodeUpdater {

	public void updateNodes(Maze maze, Player player) {
		
		//Erases the weight of all the nodes
		this.initNodes(maze.getNodes());
		
		//Get the player node and set it's weight to 0
		Node startNode = maze.getNodes()[player.getPosX()][player.getPosY()];
		startNode.setWeight(0);
		
		//Add it to the openList
		ArrayDeque<Node> openList = new ArrayDeque<Node>();
		openList.add(startNode);
		
		Node currentNode;
		List<Node> neighbors;
		
		while(!openList.isEmpty()) {
			//Pop the first element of the queue
			currentNode = openList.pop();
			//Get it's neighbors
			neighbors = getFreeNeighbors(maze, currentNode);
			for(Node n : neighbors) {
				//If it's already in we don't add it
				if(openList.contains(n))
					continue;
				//Add weight +1 to available neighbors
				n.setWeight(currentNode.getWeight()+1);
				//Add available neighbors to the openList
				openList.add(n);
			}
		}
		
	}
	
	private List<Node> getFreeNeighbors(Maze maze, Node node) {
		List<Node> neighbors = new ArrayList<Node>();
		Node north = getFreeNode(maze, node.getCell().getX(), node.getCell().getY()-1);
		Node east = getFreeNode(maze, node.getCell().getX()+1, node.getCell().getY());
		Node south = getFreeNode(maze, node.getCell().getX(), node.getCell().getY()+1);
		Node west = getFreeNode(maze, node.getCell().getX()-1, node.getCell().getY());
		if(north != null && north.getCell().isEmpty() && north.getWeight() > node.getWeight()) {
			neighbors.add(north);
		}
		if(east != null && east.getCell().isEmpty() && east.getWeight() > node.getWeight()) {
			neighbors.add(east);
		}
		if(south != null && south.getCell().isEmpty() && south.getWeight() > node.getWeight()) {
			neighbors.add(south);
		}
		if(west != null && west.getCell().isEmpty() && west.getWeight() > node.getWeight()) {
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
	
	private void initNodes(Node[][] nodes) {
		int maxValue = nodes.length*nodes[0].length;
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				nodes[i][j].setWeight(maxValue);
			}
		}
	}
	
	public static void main(String[] args) {
		ArrayDeque<Integer> openList = new ArrayDeque<Integer>();
		openList.add(1);
		openList.add(2);
		System.out.println(openList.pop());
		System.out.println(openList.pop());
	}

}
