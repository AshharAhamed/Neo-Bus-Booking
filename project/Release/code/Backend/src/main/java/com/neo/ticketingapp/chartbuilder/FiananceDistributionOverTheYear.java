package com.neo.ticketingapp.chartbuilder;

import java.util.List;

import com.neo.ticketingapp.common.constants.CommonConstants;
import com.neo.ticketingapp.model.PassengerJourneyLog;
import com.neo.ticketingapp.service.implementation.PassengerJourneysLogServiceImpl;

public class FiananceDistributionOverTheYear extends BarStatChart{

	List<PassengerJourneyLog> passengerJouneyLogs ; 
	PassengerJourneysLogServiceImpl j = new PassengerJourneysLogServiceImpl(); 
	
	@Override
	public float[] getDataArr() {

		float [] financeDistribution = {12009968, 90871900, 80090764, 34009071, 89890210, 90907921, 78892010, 87990948, 40506090, 10907809, 0, 0}; ; 
		
		// Do the implementation here
		
				
		return financeDistribution;
	}

	@Override
	public int[] getRGBColorArr() {
		return CommonConstants.GREEN_CHART_RGBA;
	}

	@Override
	public String getChartName() {
		return CommonConstants.CHART_NAME_FINANCE_OVER_YEAR;
	}

	@Override
	public float[][] getAllTableData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}