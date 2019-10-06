package com.neo.ticketingapp.chartbuilder;

import java.util.ArrayList;

public class Chart {
	private ArrayList<IChartStructure> chartStructures = new ArrayList<IChartStructure>();
	IChartStructure basic; 
	
	// Function to add an items to the list
	public void addStructure(IChartStructure s) {
		this.basic = s ; 
		chartStructures.add(s);
	}
	
	// Function to get the 
	public String getChartName() {
		return basic.getChartName();
	}
	
	// Function to get the data array 
	public float[] getChartData() {
		return basic.getDataArr(); 
	}
	
	// Function to get the data array 
	public float[][] getTableData() {
		return basic.getAllTableData();
	}
	
	// Function to get the chart type
	public String getChartType() {
		return basic.getChartype().chartType();
	}
	
	// Function to get the chart color 
	public int[] getChartColor() {
		return basic.getRGBColorArr();
	}
	
	// Function to show details
	public void showDetails() {
		for (IChartStructure c : chartStructures) {
			System.out.println(c.getChartName());
			System.out.println(c.getDataArr());
			System.out.println(c.getRGBColorArr());
		}
	}
}
