package com.example.bounswegroup2.Utils;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by yigitozgumus on 12/21/16.
 */

public class MicronutrientsValueFormatter implements IAxisValueFormatter {

    private BarLineChartBase<?> chart;

    public MicronutrientsValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    final String[] MICRONUTRIENTS = {
            "Cholestrol(mg)",
            "Magnesium(mg)",
            "Niacin(mg)",
            "Iron(mg)",
            "Calcium(mg)",
            "Sodium(mg)",
            "Potassium(mg)",
            "Phosphorus(mg)",
            "Riboflavin(mg)",
            "Folate(mg)",
            "Fibre(g)",
            "Thiamin(mg)"
    };
    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int val = (int) value;
        return MICRONUTRIENTS[val-1];
    }
}
