package com.neo.ticketingapp.chartbuilder;

import java.util.List;

import com.neo.ticketingapp.common.constants.CommonConstants;
import com.neo.ticketingapp.model.PassengerJourneyLog;
import com.neo.ticketingapp.service.implementation.PassengerJourneysLogServiceImpl;

public class FinesDistrubutionOverTheYear extends BarStatChart{

	List<PassengerJourneyLog> passengerJouneyLogs ; 
	PassengerJourneysLogServiceImpl j = new PassengerJourneysLogServiceImpl(); 
	
	@Override
	public float[] getDataArr() {

		float [] finesDistribution = {75893, 59073, 78483, 49389, 90803, 79392, 93920, 28392, 48379, 7999, 0, 0}; ; 
		
		// Do the implementation here

		return finesDistribution;
	}

	@Override
	public int[] getRGBColorArr() {
		return CommonConstants.RED_CHART_RGBA;
	}

	@Override
	public String getChartName() {
		return CommonConstants.CHARTNAME_FinesOverYear;
	}

	@Override
	public float[][] getAllTableData() {
		// TODO Auto-generated method stub
		return null;
	}
	
}