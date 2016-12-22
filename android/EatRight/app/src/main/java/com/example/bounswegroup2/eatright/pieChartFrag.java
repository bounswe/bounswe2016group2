package com.example.bounswegroup2.eatright;

/**
 * Created by yigitozgumus on 12/17/16.
 */

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bounswegroup2.Models.AteFoodUserless;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.TotalUserHistory;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.SessionManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * The type Pie chart frag.
 */
public class pieChartFrag extends userHomeFragment {

    /**
     * New instance fragment.
     *
     * @param position the position
     * @return the fragment
     */
    public static Fragment newInstance(int position) {
        pieChartFrag frag = new pieChartFrag();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        frag.setArguments(b);
        return frag;
    }

    private PieChart mChart;
    private static final String ARG_POSITION = "position";
    private int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_chart_pie, container, false);
        position = getArguments().getInt(ARG_POSITION);
        mChart = (PieChart) v.findViewById(R.id.pieChart1);
        mChart.getDescription().setEnabled(false);

        final Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        mChart.setCenterTextTypeface(tf);
        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);
        mChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);

        //Set data
        String token = "Token " + SessionManager.getPreferences(getContext(),"token");
        System.out.println(token);
        String currentDateTimeString = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());
        //System.out.println(currentDateTimeString.substring(0,8)+"=====================");
        String[] timecheck = currentDateTimeString.substring(0,8).split("/");
        System.out.println(currentDateTimeString.substring(0,8));
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
                    double carbs = 0;
                    double fats = 0;
                    double protein = 0;
                    int dayd = 0;
                    for (AteFoodUserless ate : foodList){
                        String[] checkDate = ate.getCreated().substring(2,10).split("-");
                        Integer checkDay = Integer.parseInt(checkDate[2]);
                        String[] checkTime = ate.getCreated().substring(11,19).split(":");
                        System.out.println(ate.getCreated().substring(11,19) + " ___________________");
                        Integer hour = Integer.parseInt(checkTime[0]);
                        if (hour >= 21){
                            checkDay++;
                            dayd = checkDay;
                            System.out.println(checkDay);
                        }

                        System.out.println(ate.getCreated()+ "----------------");
                        System.out.println(checkDate[0]+ " " + year);
                        System.out.println(checkDate[1]+ " " + month);
                        System.out.println(checkDay+ " " + day);
                        if(Integer.parseInt(checkDate[0]) ==year && Integer.parseInt(checkDate[1]) ==month && checkDay ==dayd){
                            System.out.println("WUBBA LUBBA DUB DUB");
                            Food food = ate.getFood();
                            carbs += food.getDetails().getCarb().getWeight();
                            fats += food.getDetails().getFat().getWeight();
                            protein += food.getDetails().getProtein().getWeight();
                        }

                    }
                    ArrayList<PieEntry> entries1 = new ArrayList<>();
                    entries1.add(new PieEntry((float) carbs,"Carbs(gr)"));
                    entries1.add(new PieEntry((float)fats,"Fats(gr)"));
                    entries1.add(new PieEntry((float) protein,"Protein(gr)"));
                    PieDataSet ds1 = new PieDataSet(entries1, "MacroNutrients");
                    ds1.setColors(ColorTemplate.COLORFUL_COLORS);
                    ds1.setSliceSpace(2f);
                    ds1.setValueTextColor(Color.BLACK);
                    ds1.setValueTextSize(12f);
                    ds1.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
                    PieData d = new PieData(ds1);
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

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Daily Consumption");
        s.setSpan(new RelativeSizeSpan(1.5f), 0, 17, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 16, s.length(), 0);
        return s;
    }
}
