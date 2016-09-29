package com.cookiebutter.ManagedBeans;

import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by MEUrena on 9/29/16.
 * All rights reserved.
 */

@ManagedBean(name = "dashboard")
@SessionScoped
public class AdminBean implements Serializable {

    private PieChartModel pieChart;
    private BarChartModel barChart;

    @PostConstruct
    public void init() {
        setupCharts();
    }

    public void setupCharts() {
        setupBarChart();
        setupPieChart();
    }

    public void setupPieChart() {
        pieChart = new PieChartModel();

        pieChart.set("Brand 1", 540);
        pieChart.set("Brand 2", 325);
        pieChart.set("Brand 3", 702);
        pieChart.set("Brand 4", 421);

        pieChart.setTitle("Simple Pie");
        pieChart.setLegendPosition("w");
    }

    public void setupBarChart() {
        barChart = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        barChart.addSeries(boys);

        barChart.setTitle("Bar Chart");
        barChart.setLegendPosition("ne");

        Axis xAxis = barChart.getAxis(AxisType.X);
        xAxis.setLabel("Gender");

        Axis yAxis = barChart.getAxis(AxisType.Y);
        yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    public PieChartModel getPieChart() {
        return pieChart;
    }

    public void setPieChart(PieChartModel pieChart) {
        this.pieChart = pieChart;
    }

    public BarChartModel getBarChart() {
        return barChart;
    }

    public void setBarChart(BarChartModel barChart) {
        this.barChart = barChart;
    }
}
