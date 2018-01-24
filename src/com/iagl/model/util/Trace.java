package com.iagl.model.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import com.iagl.model.agent.Agent;

public class Trace {

	private boolean trace;
	
	public Trace(Boolean trace, String printLocation) {
		this.trace = trace;
		if(this.trace) {
			this.setSystemPrint(printLocation);
		}
	}

	public void traceTick(int currentFrame){
		if(this.trace) {
			System.out.println("Ticks;"+currentFrame+";");
		}
	}
	
	public void traceCollision(Agent agent){
		if(this.trace){
			System.out.println("Agent;"+agent.getPosX()+";"+agent.getPosY()+";"+agent.getPasX()+";"+agent.getPasY()+";");
		}
	}	
	
	private void setSystemPrint(String printLocation) {
		if(printLocation != null) {
			try {
				System.setOut(new PrintStream(new FileOutputStream(new File(printLocation))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}		
	}
	
}
