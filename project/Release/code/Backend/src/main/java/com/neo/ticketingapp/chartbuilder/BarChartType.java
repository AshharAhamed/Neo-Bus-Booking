package com.neo.ticketingapp.chartbuilder;

import com.neo.ticketingapp.common.constants.CommonConstants;

public class BarChartType implements IChartType{

	@Override
	public String chartType() {
		return CommonConstants.BAR_CHART;
	}

}
