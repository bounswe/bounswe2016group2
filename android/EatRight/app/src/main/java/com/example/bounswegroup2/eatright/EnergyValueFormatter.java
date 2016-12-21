package com.example.bounswegroup2.eatright;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by yigitozgumus on 12/21/16.
 */
public class EnergyValueFormatter implements IAxisValueFormatter
{

    private DecimalFormat mFormat;

    /**
     * Instantiates a new Energy value formatter.
     */
    public EnergyValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + " kcal" ;
    }
}