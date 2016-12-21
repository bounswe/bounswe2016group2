package com.example.bounswegroup2.eatright;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.bounswegroup2.Models.AteFoodUserless;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.TotalUserHistory;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.DayAxisValueFormatter;
import com.example.bounswegroup2.Utils.SessionManager;
import com.example.bounswegroup2.Utils.ValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yigitozgumus on 12/19/16.
 */
public class fullHistoryEnergyFragment extends userHomeFragment {
    private static final String ARG_POSITION = "position";
    private int position;

    /**
     * New instance fragment.
     *
     * @param position the position
     * @return the fragment
     */
    public static Fragment newInstance(int position) {
        fullHistoryEnergyFragment frag = new fullHistoryEnergyFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        frag.setArguments(b);
        return frag;
    }
    private BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_full_history_energy, container, false);
        final Typeface tf1 = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        position = getArguments().getInt(ARG_POSITION);
        mChart = (BarChart) v.findViewById(R.id.stacked_bar2);
        mChart.getDescription().setEnabled(false);
        mChart.setMaxVisibleValueCount(40);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(true);
        mChart.setDrawBarShadow(true);

        mChart.setDrawValueAboveBar(false);
        mChart.setHighlightFullBarEnabled(false);

        // change the position of the y-labels
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setValueFormatter(new EnergyValueFormatter());
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        mChart.getAxisRight().setEnabled(false);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);

        //set the data
        String token = "Token " + SessionManager.getPreferences(getContext(),"token");
        System.out.println(token);
        String currentDateTimeString = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date());
        System.out.println(currentDateTimeString.substring(0,8)+"=====================");
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

                    double energy = 0.0;
                    BarDataSet set1;
                    String dateCheck;
                    ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
                    if(foodList.size() != 0){
                        Food initialFood = foodList.get(0).getFood();
                        String[] checkDate = foodList.get(0).getCreated().substring(2,10).split("-");
                        String checkMonth = getMonthEquivalent(Integer.parseInt(checkDate[1]));
                        Integer checkDay = Integer.parseInt(checkDate[2]);
                        String[] checkTime = foodList.get(0).getCreated().substring(11,19).split(":");
                        Integer hour = Integer.parseInt(checkTime[0]);
                        if (hour >= 21){
                            checkDay++;
                        }
                        int dateResolver = getTotalDays(Integer.parseInt(checkDate[1]),checkDay);
                        System.out.println("DEBUG "+ Integer.parseInt(checkDate[1])+ " "+ checkDay);
                        System.out.println(dateResolver);

                        energy += initialFood.getDetails().getEnergy();
                        dateCheck = checkMonth+"-"+String.valueOf(checkDay);

                        //System.out.println(dateCheck);
                        Integer count = 1;
                        Integer indexCount = 0;
                        for (int i =1;i < foodList.size();i++){
                            AteFoodUserless ate = foodList.get(i);
                            Food food = ate.getFood();
                            String[] checkDate1 = foodList.get(i).getCreated().substring(2,10).split("-");
                            String checkMonth1 = getMonthEquivalent(Integer.parseInt(checkDate1[1]));
                            Integer checkDay1 = Integer.parseInt(checkDate1[2]);
                            String[] checkTime1 = foodList.get(i).getCreated().substring(11,19).split(":");

                            Integer hour1 = Integer.parseInt(checkTime1[0]);
                            if (hour1 >= 21){
                                checkDay1++;
                            }
                            //System.out.println((checkMonth1+"-"+String.valueOf(checkDay1) + " !!!!! " + dateCheck));
                            if(!(checkMonth1+"-"+String.valueOf(checkDay1)).equals(dateCheck)){
                                yVals1.add(new BarEntry(indexCount+dateResolver,
                                        new float[]{(float)energy},
                                        dateCheck));
                                count++;
                                indexCount++;
                                energy = initialFood.getDetails().getEnergy();
                                dateCheck =checkMonth1+"-"+String.valueOf(checkDay1);
                            }else {
                                energy += initialFood.getDetails().getEnergy();
                            }
                        }
                        yVals1.add(new BarEntry(indexCount+dateResolver,
                                new float[]{(float)energy}
                               , dateCheck));
                        set1 = new BarDataSet(yVals1, "Food Consumption History");
                        set1.setColors(getColors());
                        set1.setStackLabels(new String[]{"Total Energy"});

                        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
                        dataSets.add(set1);

                        BarData data = new BarData(dataSets);
                        data.setValueFormatter(new ValueFormatter());
                        data.setValueTextColor(Color.WHITE);
                        data.setValueTypeface(tf1);
                        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);
                        XAxis xAxis = mChart.getXAxis();
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xAxis.setTypeface(tf1);
                        xAxis.setDrawGridLines(false);
                        xAxis.setGranularity(1f); // only intervals of 1 day
                        xAxis.setLabelCount(7);
                        xAxis.setValueFormatter(xAxisFormatter);
                        mChart.setData(data);
                        mChart.invalidate();
                    }


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

    private int[] getColors() {

        int stacksize = 3;

        // have as many colors as stack-values per entry
        int[] colors = new int[stacksize];

        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.PASTEL_COLORS[i];
        }

        return colors;
    }
    private int getTotalDays(int month,int day){
        int result = 0;
        if(month == 1){
            return day;}
        else {
            for (int i = 1; i <month ; i++) {
                if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 ){
                    result += 31;
                }else if(i ==2){
                    result += 28;
                }else{
                    result += 30;
                }
            }
            result += day;
            return result;
        }
    }
}
