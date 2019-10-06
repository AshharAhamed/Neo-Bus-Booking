package com.neo.ticketingapp.service.implementation;

import com.neo.ticketingapp.chartbuilder.Chart;
import com.neo.ticketingapp.chartbuilder.ChartBuilder;
import com.neo.ticketingapp.service.interfaces.IChartService;

import org.json.simple.JSONObject;

public class ChartServiceImpl implements IChartService{

	ChartBuilder cb = new ChartBuilder() ; 

	@Override
	public JSONObject getChartData(String type) {
		JSONObject charDataJSON = new JSONObject();
		Chart tempChart = cb.buildChart(type);; 

		// Load JSON
		charDataJSON.put("type", tempChart.getChartType()) ;
		charDataJSON.put("name", tempChart.getChartName()) ; 
		charDataJSON.put("color", tempChart.getChartColor()) ; 
		
		// Get data based on the request type
		if("allFinanceTable".equals(type) || "allFinesTable".equals(type)) {
			charDataJSON.put("data", tempChart.getTableData()) ; 
		}else {
			charDataJSON.put("data", tempChart.getChartData()) ; 
		}
		
		
		// Return JSON object
		return charDataJSON;
	}

}
