package com.iagl.avatar.agent;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.iagl.avatar.map.Maze;
import com.iagl.core.agent.Agent;
import com.iagl.core.map.Cell;
import com.iagl.core.map.Environment;

public class Player extends Agent implements KeyListener {
	
	private boolean[] directions;
	
	private static final int NORTH = 0;
	
	private static final int EAST = 1;
	
	private static final int SOUTH = 2;
	
	private static final int WEST = 3;
	
	private int maxDecidePlayer;
	
	private int decidePlayer;

	public Player(int posX, int posY, int decidePlayer) {
		super(posX, posY);
		this.color = Color.BLUE;
		directions = new boolean[4];
		for(int i=0; i < 4; i++) {
			directions[i] = false;
		}
		this.maxDecidePlayer = this.decidePlayer = decidePlayer;
	}

	@Override
	public void update() {
		if(this.decidePlayer > 0) {
			this.decidePlayer--;
		}
	}

	@Override
	public void decide(Environment env) {
		
		if(decidePlayer > 0) {
			return;
		} else {
			decidePlayer = maxDecidePlayer;
		}
		
		int newX = this.posX;
		int newY = this.posY;
		if(directions[NORTH]) {
			newY--;
		} else if(directions[EAST]) {
			newX++;
		} else if(directions[SOUTH]) {
			newY++;
		} else if(directions[WEST]) {
			newX--;
		}
		
		Cell newCell = env.getCells(newX, newY);
		if(newCell != null && newCell.isEmpty()) {
			this.move(env, newCell);
			((Maze)env).updateNodes();
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				go(NORTH);
				break;
			case KeyEvent.VK_RIGHT:
				go(EAST);
				break;
			case KeyEvent.VK_DOWN:
				go(SOUTH);
				break;
			case KeyEvent.VK_LEFT:
				go(WEST);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
//		switch(e.getKeyCode()) {
//		case KeyEvent.VK_UP:
//			directions[NORTH] = false;
//			break;
//		case KeyEvent.VK_RIGHT:
//			directions[EAST] = false;
//			break;
//		case KeyEvent.VK_DOWN:
//			directions[SOUTH] = false;
//			break;
//		case KeyEvent.VK_LEFT:
//			directions[WEST] = false;
//			break;
//		}
	}
	
	private void go(int direction) {
		for(int i=0; i < 4; i++) {
			directions[i] = (direction==i);
		}
	}

}
