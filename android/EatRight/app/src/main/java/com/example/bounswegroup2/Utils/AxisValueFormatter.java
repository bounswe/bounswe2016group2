package com.example.bounswegroup2.Utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by yigitozgumus on 12/20/16.
 */

public class AxisValueFormatter implements IAxisValueFormatter
{

    private DecimalFormat mFormat;

    public AxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) ;
    }
}