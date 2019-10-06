package com.neo.ticketingapp.chartbuilder;

public class ChartBuilder {
	
	// Function to build the chart 
	public Chart buildChart(String type) {
		
		Chart c = new Chart();
		
		if("passengerDistributionOverDay".equals(type)) {
			c.addStructure(new PassengerDistributionOverDay());
    	}else if("passengerDistributionOverWeek".equals(type)){
    		c.addStructure(new PassengerDistributionOverWeek());
    	}else if("financeOverYear".equals(type)) {
    		c.addStructure(new FiananceDistributionOverTheYear());
    	}else if("finesOverYear".equals(type)){
    		c.addStructure(new FinesDistrubutionOverTheYear());
    	}else if("financeGrowthYearly".equals(type)) {
    		c.addStructure(new FiananceGrowthOverTheYears());
    	}else if("fraudsGrowthYearly".equals(type)){
    		c.addStructure(new FraudGrowthOverTheYears());
    	}else if("allFinanceTable".equals(type)) {
    		c.addStructure(new FaresOverTheYear());
    	}else {
    		c.addStructure(new FinesOverTheYear());
    	}
		
		return c ; 
	}
	
	
}
