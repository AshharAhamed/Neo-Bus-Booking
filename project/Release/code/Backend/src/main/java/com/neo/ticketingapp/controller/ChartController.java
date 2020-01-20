package com.neo.ticketingapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neo.ticketingapp.service.implementation.ChartServiceImpl;

@CrossOrigin(origins = {"http://localhost:3000", "https://neo-bus-frontend.herokuapp.com"})
@RestController
@RequestMapping("/chart")
public class ChartController {
    private static final Logger logger = LogManager.getLogger(ChartController.class);

    ChartServiceImpl chartService = new ChartServiceImpl();

    @GetMapping(value = "/{type}")
    public JSONObject getpassengerDistributionOverDay(@PathVariable String type) {
        logger.debug("Request received to get stat chart data");

        if ("passengerDistributionOverDay".equals(type)) {
            return chartService.getChartData("passengerDistributionOverDay");
        } else if ("passengerDistributionOverWeek".equals(type)) {
            return chartService.getChartData("passengerDistributionOverWeek");
        } else if ("financeOverYear".equals(type)) {
            return chartService.getChartData("financeOverYear");
        } else if ("finesOverYear".equals(type)) {
            return chartService.getChartData("finesOverYear");
        } else if ("financeGrowthYearly".equals(type)) {
            return chartService.getChartData("financeGrowthYearly");
        } else if ("fraudsGrowthYearly".equals(type)) {
            return chartService.getChartData("fraudsGrowthYearly");
        } else if ("allFinanceTable".equals(type)) {
            return chartService.getChartData("allFinanceTable");
        } else {
            return chartService.getChartData("allFinesTable");
        }


    }

}
