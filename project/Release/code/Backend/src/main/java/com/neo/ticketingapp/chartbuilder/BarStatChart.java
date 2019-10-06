package com.neo.ticketingapp.chartbuilder;

public abstract class BarStatChart implements IChartStructure{

	@Override
	public IChartType getChartype() {
		return (new BarChartType());
	}
	
}
