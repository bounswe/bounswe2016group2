package com.example.bounswegroup2.Utils;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by yigitozgumus on 12/20/16.
 */
public class ValueFormatter implements IValueFormatter
{

    private DecimalFormat mFormat;

    /**
     * Instantiates a new Value formatter.
     */
    public ValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value);
    }
}
