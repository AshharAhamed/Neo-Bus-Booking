package com.neo.ticketingapp.chartbuilder;

import com.neo.ticketingapp.common.constants.CommonConstants;

public class PassengerDistributionOverWeek extends BarStatChart{

	@Override
	public float[] getDataArr() {
		float [] passengerDistribution = {315, 122, 670, 435, 325, 776, 240};
		
		// Do the implementation here
				
		return passengerDistribution;
	}

	@Override
	public int[] getRGBColorArr() {
		return CommonConstants.ORANGE_CHART_RGBA;
	}

	@Override
	public String getChartName() {
		return CommonConstants.CHARTNAME_LoadOverWeek;
	}

	@Override
	public float[][] getAllTableData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

