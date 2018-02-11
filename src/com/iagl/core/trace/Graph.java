package com.iagl.core.trace;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class Graph {

	private File traceLocation;
	
	private Map<String, Color> inputColor;
	
	public Graph(String fileLocation) throws FileNotFoundException {
		this.inputColor = new HashMap<String, Color>();
		this.loadFile(fileLocation);
	}
	
	public void registerColor(String inputName, Color color) {
		this.inputColor.put(inputName, color);
	}

	private void loadFile(String fileLocation) throws FileNotFoundException {
		this.traceLocation = new File(fileLocation);
	}
	
	public void displayGraph(String displayName, int width, int height) {
		JFrame frame = new JFrame(displayName);
		frame.setContentPane(new DrawPlotPanel(width, height, this.getMetrics(), this.inputColor));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.repaint();
	}
	
	private Map<String, List<Integer>> getMetrics() {
		String line, metricName;
		Integer value;
		String[] values;
		Map<String, List<Integer>> metrics = new HashMap<String, List<Integer>>();
		try(BufferedReader reader = new BufferedReader(new FileReader(this.traceLocation))){
			while((line = reader.readLine()) != null) {
				values = line.split(" ");
				metricName = values[0];
				value = Integer.valueOf(values[1]);
				if(metricName.equals("TICK"))
					continue;
				if(!metrics.containsKey(metricName)) {
					metrics.put(metricName, new ArrayList<Integer>());
				}
				metrics.get(metricName).add(value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return metrics;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Graph collisionGraph = new Graph("log-particle.csv");
		collisionGraph.registerColor("COLLISION", Color.RED);
		collisionGraph.displayGraph("Collisions", 1000, 500);
//		Graph watorGraph = new Graph("log-wator.csv");
//		watorGraph.registerColor("FISH", Color.GREEN);
//		watorGraph.registerColor("SHARK", Color.RED);
//		watorGraph.displayGraph("Wator", 1000, 500);
	}


}
