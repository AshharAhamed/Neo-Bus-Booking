package com.neo.ticketingapp.chartbuilder;

import java.util.List;

import com.neo.ticketingapp.common.constants.CommonConstants;
import com.neo.ticketingapp.model.PassengerJourneyLog;
import com.neo.ticketingapp.service.implementation.PassengerJourneysLogServiceImpl;

public class PassengerDistributionOverDay extends BarStatChart{

	List<PassengerJourneyLog> passengerJouneyLogs ; 
	PassengerJourneysLogServiceImpl j = new PassengerJourneysLogServiceImpl(); 
	
	@Override
	public float[] getDataArr() {

		float [] passengerDistribution = {1, 5, 34, 90, 67, 87, 71, 24}; ; 
		
		// Do the implementation here
		
				
		return passengerDistribution;
	}

	@Override
	public int[] getRGBColorArr() {
		return CommonConstants.BLUE_CHART_RGBA;
	}

	@Override
	public String getChartName() {
		return CommonConstants.CHARTNAME_LoadOverDay;
	}

	@Override
	public float[][] getAllTableData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
