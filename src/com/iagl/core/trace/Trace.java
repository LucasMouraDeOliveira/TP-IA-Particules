package com.iagl.core.trace;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Trace {
	
	private File traceLocation;
	
	private int tick;
	
	private BufferedWriter writer;
	
	public Trace(String fileName) {
		this.tick = 0;
		try {
			this.setSystemPrint(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void nextTick() {
		this.tick++;
		this.writeLine("TICK " + this.tick);
	}
	
	public void writeLine(String text) {
		try {
			this.writer.write(text+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void close() {
		try {
			this.writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setSystemPrint(String printLocation) throws IOException {
		this.traceLocation = new File(printLocation);
		this.writer = new BufferedWriter(new FileWriter(this.traceLocation));
	}
	
}
