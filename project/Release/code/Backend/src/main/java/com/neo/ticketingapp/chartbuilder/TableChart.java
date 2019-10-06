package com.neo.ticketingapp.chartbuilder;

public abstract class TableChart implements IChartStructure{

	@Override
	public IChartType getChartype() {
		return (new TableChartType());
	}
	
	public float[][] getAllTableData() {
		return null;
	}
	
}
