package com.iagl.core.trace;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class DrawPlotPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1165570157750398358L;
	
	private int width;
	
	private int height;
	
	private double pixelPerPointWidth;
	
	private double pixelPerPointHeight;
	
	private Map<String, List<Integer>> metrics;
	
	private Map<String, Color> colors;
	
	public DrawPlotPanel(int width, int height, Map<String, List<Integer>> metrics, Map<String, Color> colors) {
		this.width = width;
		this.height = height;
		this.metrics = metrics;
		this.colors = colors;
		this.setPreferredSize(new Dimension(width, height));
		this.calculateMetricDisplay();
	}

	private void calculateMetricDisplay() {
		int points = this.metrics.get(this.metrics.keySet().toArray()[0]).size();
		int maxValue = this.getMaxValue();
		this.pixelPerPointWidth = (double)this.width/points;
		this.pixelPerPointHeight = (double)this.height/maxValue;
	}

	private int getMaxValue() {
		int maxValue = 0;
		for(List<Integer> metric : this.metrics.values()) {
			for(Integer value : metric) {
				maxValue = Math.max(value, maxValue);
			}
		}
		return maxValue;
	}

	@Override
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		List<Integer> metricValues;
		double position;
		
		for(String metricName : metrics.keySet()) {
			g.setColor(this.colors.get(metricName));
			metricValues = metrics.get(metricName);
			position = 0.0D;
			for(int i = 1; i < metricValues.size(); i++) {
				int posY1OnScreen = this.getPosYOnScreen(metricValues.get(i-1));
				int posY2OnScreen = this.getPosYOnScreen(metricValues.get(i));
				g.drawLine((int)position, posY1OnScreen, (int)(position+this.pixelPerPointWidth), posY2OnScreen);
				position+=this.pixelPerPointWidth;
				
			}
		}
		
	}

	private int getPosYOnScreen(Integer value) {
		double normalizedValue = ((double)value)*this.pixelPerPointHeight;
		int troncatedValue = (int)normalizedValue;
		int reversedValue = this.height-troncatedValue;
		return reversedValue;
	}
	
}
