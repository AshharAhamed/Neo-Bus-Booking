package com.neo.ticketingapp.common.constants;

public class CommonConstants {

    private CommonConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Double ONE_HUNDRED = 100d;
    public static final Double STUDENT_DISCOUNT = 6d;
    public static final Double SEASONAL_DISCOUNT = 99d;
    public static final Double DISABLED_DISCOUNT = 5d;
    public static final Double NEW_FOREIGNER_DISCOUNT = 98d;
    public static final Double NEW_LOCAL_DISCOUNT = 98d;
    public static final String ERROR = "Error";
    public static final String VALID = "Valid";
    public static final String SUCCESS = "SUCCESS";

    public static final String BAR_CHART = "BarChart";
    public static final String LINE_CHART = "LineChart";
    public static final String TABLE_CHART = "Table";
    public static final String CHART_NAME_LOAD_OVER_DAY = "Passenger Distribution Over a Day";
    public static final String CHART_NAME_LOAD_OVER_WEEK = "Passenger Distribution Over a Week";

    public static final String CHART_NAME_ALL_FINANCE_TABLE = "Total Earnings from Each Route";
    public static final String CHART_NAME_FINANCE_OVER_YEAR = "Earnings Distribution Over the Year";
    public static final String CHART_NAME_FINANCE_GROWTH = "Fianance Growth Over Years";
    public static final String CHART_NAME_ALL_FINES_TABLE = "Fines Collected by Each Route";
    public static final String CHART_NAME_FRAUD_GROWTH = "Fines Reported Over the Years";
    public static final String CHART_NAME_FINES_OVER_YEAR = "Fines Distribution Over the Year";

    public static final int[] BLUE_CHART_RGBA = {51, 176, 254, 1};
    public static final int[] ORANGE_CHART_RGBA = {247, 112, 66, 1};
    public static final int[] RED_CHART_RGBA = {254, 64, 67, 1};
    public static final int[] GREEN_CHART_RGBA = {15, 159, 7, 1};
}
