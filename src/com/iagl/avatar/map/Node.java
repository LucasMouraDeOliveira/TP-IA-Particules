package com.iagl.avatar.map;

import com.iagl.core.map.Cell;

public class Node {
	
	private Cell cell;
	
	private int weight;
	
	public Node(Cell cell) {
		this.cell = cell;
		this.weight = -1;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

}
