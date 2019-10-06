package com.neo.ticketingapp.chartbuilder;

public abstract class GrowthChart implements IChartStructure{

	@Override
	public IChartType getChartype() {
		return (new LineChartType());
	}
	
}