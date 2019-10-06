package com.neo.ticketingapp.model;

public class Chart {
	int [] color ; // Array to hold the color 
	float [] data ;
	
	// Default constructor 
	public Chart() {}
	
	// Constructor with fields
	public Chart(int[] color, float[] data) {
		super();
		this.color = color;
		this.data = data;
	}

	// Getters and setters 
	public int[] getColor() {
		return color;
	}

	public void setColor(int[] color) {
		this.color = color;
	}

	public float[] getData() {
		return data;
	}

	public void setData(float[] data) {
		this.data = data;
	} 
	
	
}
