package com.neo.ticketingapp.chartbuilder;

import com.neo.ticketingapp.common.constants.CommonConstants;

public class FraudGrowthOverTheYears extends GrowthChart{

	@Override
	public float[] getDataArr() {
		float [] fraudGrowth = {908973, 621222, 870733, 535532, 625111, 453776, 378240, 468325, 290776, 100240};
		
		// Do the implementation here
				
		return fraudGrowth;
	}

	@Override
	public int[] getRGBColorArr() {
		return CommonConstants.RED_CHART_RGBA;
	}

	@Override
	public String getChartName() {
		return CommonConstants.CHARTNAME_FraudGrowth;
	}

	@Override
	public float[][] getAllTableData() {
		// TODO Auto-generated method stub
		return null;
	}
}