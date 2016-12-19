package com.example.bounswegroup2.eatright;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;

/**
 * Created by yigitozgumus on 12/19/16.
 */

public class FullHistoryFrag extends userHomeFragment {

    public static Fragment newInstance() {
        return new FullHistoryFrag();
    }
    private BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_full_history, container, false);

        return v;

        }
    }
