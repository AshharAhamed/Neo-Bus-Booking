package com.neo.ticketingapp.chartbuilder;

import com.neo.ticketingapp.common.constants.CommonConstants;

public class FinesOverTheYear extends TableChart{

	@Override
	public float[] getDataArr() {
		// This is not implemented 
		return null;
	}
	
	@Override
	public float[][] getAllTableData() {

		float[][] fineseData = {
				{177, 8398, 7338, 8228, 8392, 8378, 6273, 8928, 9382, 7328, 1392, 0, 0},
				{154, 7327, 4678, 5569, 5537, 4643, 2839, 3803, 4235, 2353, 1253, 0, 0},
				{138, 5246, 5567, 5545, 3932, 3456, 3545, 3524, 3432, 5466, 1532, 0, 0},
				{168, 7686, 5743, 5554, 7779, 4675, 3474, 5684, 5455, 6845, 1545, 0, 0},
				{163, 6537, 4577, 4875, 7658, 3728, 3454, 6864, 5234, 4223, 1312, 0, 0},
				{183, 6444, 3233, 7555, 7655, 3568, 4544, 3567, 7967, 3423, 1432, 0, 0},
				{190, 4344, 7545, 8268, 5444, 2653, 3545, 5785, 4574, 2432, 1335, 0, 0},
				{689, 5464, 5555, 4344, 7557, 2464, 2646, 6876, 4434, 5755, 2333, 0, 0},
				{171, 8573, 4466, 4347, 8932, 6445, 5267, 6843, 1243, 3423, 1832, 0, 0},
				{17 , 4325, 4546, 4345, 4534, 3676, 4682, 2341, 7635, 4563, 2652, 0, 0}
		};
		
		return fineseData;
	}

	@Override
	public int[] getRGBColorArr() {
		return CommonConstants.RED_CHART_RGBA;
	}

	@Override
	public String getChartName() {
		return CommonConstants.CHARTNAME_AllFinesTable;
	}

}