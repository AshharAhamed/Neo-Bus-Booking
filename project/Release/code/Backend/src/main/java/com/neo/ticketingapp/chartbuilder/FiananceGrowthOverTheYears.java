package com.neo.ticketingapp.chartbuilder;

import com.neo.ticketingapp.common.constants.CommonConstants;

public class FiananceGrowthOverTheYears extends GrowthChart{

	@Override
	public float[] getDataArr() {
		float [] financeGrowth = {315352, 521222, 670733, 435532, 525111, 453776, 678240, 768325, 890776, 1200240};
		
		// Do the implementation here
				
		return financeGrowth;
	}

	@Override
	public int[] getRGBColorArr() {
		return CommonConstants.GREEN_CHART_RGBA;
	}

	@Override
	public String getChartName() {
		return CommonConstants.CHART_NAME_FINANCE_GROWTH;
	}

	@Override
	public float[][] getAllTableData() {
		// TODO Auto-generated method stub
		return null;
	}
}
