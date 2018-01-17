package com.iagl.launcher;

import com.iagl.model.map.Environment;
import com.iagl.model.sma.SMA;
import com.iagl.view.Window;

public class Main {
	
	public static void main(String[] args) {
		Environment env = new Environment(80, 80, 50);
		SMA sma = new SMA(env, 20);
		Window window = new Window(sma);
		window.start();
		new Thread(sma).start();
	}

}
