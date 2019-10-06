package com.neo.ticketingapp.chartbuilder;

import com.neo.ticketingapp.common.constants.CommonConstants;

public class FaresOverTheYear extends TableChart{

	@Override
	public float[] getDataArr() {
		// This is not implemented 
		return null;
	}

	@Override
	public float[][] getAllTableData() {

		float[][] financeData = {
				{177, 83928, 73838, 82928, 83932, 83728, 62733, 83928, 93832, 73628, 18392, 0, 0},
				{154, 73827, 44367, 55339, 55337, 34643, 92839, 93803, 43235, 23353, 12353, 0, 0},
				{138, 54246, 85567, 55745, 83932, 34564, 35745, 34524, 34532, 54566, 15312, 0, 0},
				{168, 97686, 57343, 57554, 77679, 45675, 34574, 45684, 35455, 56845, 15445, 0, 0},
				{163, 65375, 45757, 45755, 76568, 83728, 23454, 76864, 54234, 45223, 13312, 0, 0},
				{183, 64464, 32333, 75455, 76545, 35678, 45644, 34567, 78967, 34523, 14432, 0, 0},
				{190, 43544, 75455, 82928, 54344, 24653, 34545, 57685, 34574, 23432, 13135, 0, 0},
				{689, 54644, 55555, 64344, 75457, 24564, 24646, 67876, 43434, 45755, 23533, 0, 0},
				{171, 89573, 44646, 64347, 83932, 64445, 45267, 68743, 12343, 34523, 18392, 0, 0},
				{17 , 43235, 45436, 64345, 64534, 35676, 45682, 23341, 76835, 34563, 26452, 0, 0}
		};
		
		return financeData;
	}

	@Override
	public int[] getRGBColorArr() {
		return CommonConstants.GREEN_CHART_RGBA;
	}

	@Override
	public String getChartName() {
		return CommonConstants.CHARTNAME_AllFinanceTable;
	}

}
