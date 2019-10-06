package com.neo.ticketingapp.chartbuilder;

public interface IChartStructure {
	public IChartType getChartype() ; 
	public float[] getDataArr() ; 
	public int[] getRGBColorArr() ; 
	public String getChartName() ; 
	public float[][] getAllTableData() ; 
}
