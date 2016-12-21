package com.example.bounswegroup2.eatright;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bounswegroup2.Models.AteFoodUserless;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.TotalUserHistory;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.MicronutrientsValueFormatter;
import com.example.bounswegroup2.Utils.SessionManager;
import com.example.bounswegroup2.Utils.XYMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yigitozgumus on 12/20/16.
 */

public class MicroPieChartFragment extends userHomeFragment implements OnChartValueSelectedListener {

    private BarChart mChart;
    private static final String ARG_POSITION = "position";
    private int position;
    final int[] COLORFUL_COLORS = {
            Color.rgb(193, 37, 82),
            Color.rgb(255, 102, 0),
            Color.rgb(245, 199, 0),
            Color.rgb(106, 150, 31),
            Color.rgb(179, 100, 53) ,
            Color.rgb(64, 89, 128),
            Color.rgb(149, 165, 124),
            Color.rgb(217, 184, 162),
            Color.rgb(191, 134, 134),
            Color.rgb(179, 48, 80),
            Color.rgb(254, 149, 7),
            Color.rgb(254, 247, 120)};

    public static Fragment newInstance(int position) {
        MicroPieChartFragment frag = new MicroPieChartFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        frag.setArguments(b);
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_micro_chart_pie, container, false);

        position = getArguments().getInt(ARG_POSITION);
        mChart = (BarChart) v.findViewById(R.id.microPieChart);
        mChart.getDescription().setEnabled(false);

        final Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");


        mChart.setHighlightPerTapEnabled(true);
        IAxisValueFormatter xAxisFormatter = new MicronutrientsValueFormatter(mChart);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTypeface(tf);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // don't touch, magic
        xAxis.setTextColor(Color.WHITE);
        xAxis.setValueFormatter(xAxisFormatter);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);

        XYMarkerView mv = new XYMarkerView(getActivity(), xAxisFormatter);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart
        //Set data
        String token = "Token " + SessionManager.getPreferences(getContext(),"token");
        System.out.println(token);
        String currentDateTimeString = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());
        //System.out.println(currentDateTimeString.substring(0,8)+"=====================");
        String[] timecheck = currentDateTimeString.substring(0,8).split("/");
        final Integer day = Integer.parseInt(timecheck[1]);
        final Integer month = Integer.parseInt(timecheck[0]);
        final Integer year = Integer.parseInt(timecheck[2]);
        ApiInterface[] test = {ApiInterface.retrofit.create(ApiInterface.class)};
        Call<TotalUserHistory> cb = test[0].getuserFoodHistory(token);
        cb.enqueue(new Callback<TotalUserHistory>() {
            @Override
            public void onResponse(Call<TotalUserHistory> call, Response<TotalUserHistory> response) {
                if(response.isSuccessful()){
                    TotalUserHistory userHistory = response.body();
                    ArrayList<AteFoodUserless> foodList = (ArrayList<AteFoodUserless>) userHistory.getTotal().getAteFoods();
                    double cholestrol   = 0;
                    double magnesium    = 0;
                    double niacin       = 0;
                    double iron         = 0;
                    double calcium      = 0;
                    double sodium       = 0;
                    double potassium    = 0;
                    double phosphorus   = 0;
                    double riboflavin   = 0 ;
                    double folate       = 0;
                    double fibre        = 0;
                    double thiamin      = 0;
                    ArrayList<IBarDataSet> sets = new ArrayList<IBarDataSet>();
                    for (AteFoodUserless ate : foodList){

                        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

                        String[] checkDate = ate.getCreated().substring(2,10).split("-");
                        Integer checkDay = Integer.parseInt(checkDate[2]);
                        String[] checkTime = ate.getCreated().substring(11,19).split(":");
                        // System.out.println(ate.getCreated().substring(12,20));
                        Integer hour = Integer.parseInt(checkTime[0]);
                        if (hour >= 21){
                            checkDay++;
                        }

                        // System.out.println(ate.getCreated()+ "----------------");
                        if(Integer.parseInt(checkDate[0]) ==year && Integer.parseInt(checkDate[1]) ==month && checkDay ==day){
                            Food food = ate.getFood();
                            cholestrol   += food.getDetails().getOthers().getCholesterol();
                            magnesium    += food.getDetails().getOthers().getMagnesium();
                            niacin       += food.getDetails().getOthers().getNiacin();
                            iron         += food.getDetails().getOthers().getIron();
                            calcium      += food.getDetails().getOthers().getCalcium();
                            sodium       += food.getDetails().getOthers().getSodium();
                            potassium    += food.getDetails().getOthers().getPotassium();
                            phosphorus   += food.getDetails().getOthers().getPhosphorus();
                            riboflavin   += food.getDetails().getOthers().getRiboflavin();
                            folate       += food.getDetails().getOthers().getFolate();
                            fibre        += food.getDetails().getOthers().getFibre();
                            thiamin      += food.getDetails().getOthers().getThiamin();
                        }

                    }
                    ArrayList<BarEntry> entries1 = new ArrayList<>();
                    entries1.add(new BarEntry(1,(float)cholestrol ));
                    entries1.add(new BarEntry(2,(float)magnesium  ));
                    entries1.add(new BarEntry(3,(float)niacin     ));
                    entries1.add(new BarEntry(4,(float)iron       ));
                    entries1.add(new BarEntry(5,(float)calcium    ));
                    entries1.add(new BarEntry(6,(float)sodium     ));
                    entries1.add(new BarEntry(7,(float)potassium  ));
                    entries1.add(new BarEntry(8,(float)phosphorus ));
                    entries1.add(new BarEntry(9,(float)riboflavin ));
                    entries1.add(new BarEntry(10,(float)folate    ));
                    entries1.add(new BarEntry(11,(float)fibre     ));
                    entries1.add(new BarEntry(12,(float)thiamin   ));
                   BarDataSet ds = new BarDataSet(entries1,"Micronutrients");
                    ds.setColors(COLORFUL_COLORS);
                    sets.add(ds);
                   // PieDataSet ds1 = new PieDataSet(entries1, "MacroNutrients");

//                    ds1.setColors(COLORFUL_COLORS);
//                    ds1.setSliceSpace(2f);
//                    ds1.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    BarData d = new BarData(ds);
                    d.setValueTypeface(tf);
                    mChart.setData(d);
                    mChart.invalidate();

                }
            }

            @Override
            public void onFailure(Call<TotalUserHistory> call, Throwable t) {
                System.out.println(t.getCause());
                System.out.println(t.getMessage());
            }
        });
        return v;
    }
    protected RectF mOnValueSelectedRectF = new RectF();

    @SuppressLint("NewApi")
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        mChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + mChart.getLowestVisibleX() + ", high: "
                        + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {

    }


    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Daily Consumption");
        s.setSpan(new RelativeSizeSpan(1.5f), 0, 17, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 16, s.length(), 0);
        return s;
    }

    }
