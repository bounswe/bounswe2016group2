package com.example.bounswegroup2.eatright;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Range;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Inclusion;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.QueryWrapper;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;


/**
 * A simple {@link ListFragment} subclass.
 */
public class FoodSearchFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private String query;
    private EditText allergicEditText;
    private Button searchButton;
    private ListView lv;
    private MultiAutoCompleteTextView mtext1;
    private View headerView;
    private TextView tvName;
    private TextView tvRating;
    private RangeSeekBar<Integer> calorieSeekBar;
    private RangeSeekBar<Integer> proSeekBar;
    private RangeSeekBar<Integer> fatSeekBar;
    private RangeSeekBar<Integer> carbSeekBar;
    public void setArgs(String param1){
        Bundle args = new Bundle();
        args.putString("PARAM1",param1);
        this.setArguments(args);
    }
    public FoodSearchFragment() {
        // Required empty public constructor
        query = "";
    }


    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_food_search, container, false);
        searchButton = (Button)rootView.findViewById(R.id.searchFood);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> items = Arrays.asList(mtext1.getText().toString().split("\\s*,\\s*"));
                getAllFoods(query,items);
            }
        });
        headerView = inflater.inflate(R.layout.food_list_header,null);
        tvName = (TextView) headerView.findViewById(R.id.food_list_header_name);
        tvRating = (TextView) headerView.findViewById(R.id.food_list_header_rating);
        lv = (ListView) rootView.findViewById(R.id.ingr_listview);
        calorieSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekbarForCalorie);
        fatSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekBarForFat);
        proSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.rangeSeekBarForPro);
        carbSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekBarForCab);

        //TableLayout headerView = (TableLayout) inflater.inflate(R.layout.ingr_list_header,null);
        //lv.addHeaderView(headerView);

        mtext1 = (MultiAutoCompleteTextView)rootView.findViewById(R.id.multiAutoCompleteTextView1);
        return  rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstancesState) {
        //onResume happens after onStart and onActivityCreate
        super.onActivityCreated(savedInstancesState);
        //getAllFoods(query)
        getListView().setOnItemClickListener(this);
        lv.setOnItemClickListener(this);
        getAllIngredients();
        getListView().addHeaderView(headerView);
        getListView().setDivider(ContextCompat.getDrawable(FoodSearchFragment.this.getContext(),android.R.color.black));
        getListView().setDividerHeight(1);
        //setRetainInstance(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        if(adapter.getId() == getListView().getId()){
            Food food = (Food)adapter.getAdapter().getItem(position);
            Intent intent = new Intent(getActivity(), FoodPageActivity.class);
            intent.putExtra("food", food);
            startActivity(intent);
        }
    }

    //Revise after server deployment
    private void getAllFoods(String queryString, final List<String> allergic){
        if(queryString == null) queryString = "";
        int minCalorie = calorieSeekBar.getSelectedMinValue();
        int maxCalorie = calorieSeekBar.getSelectedMaxValue();
        final Range<Integer> r1 = new Range<Integer>(minCalorie,maxCalorie);
        int minProtein = proSeekBar.getSelectedMinValue();
        int maxProtein = proSeekBar.getSelectedMaxValue();
        final Range<Integer> r2 = new Range<Integer>(minProtein,maxProtein);
        final int minCarbon  = carbSeekBar.getSelectedMinValue();
        final int maxCarbon  = carbSeekBar.getSelectedMaxValue();
        final Range<Integer> r3 = new Range<Integer>(minCarbon,maxCarbon);
        int minFat = fatSeekBar.getSelectedMinValue();
        int maxFat = fatSeekBar.getSelectedMaxValue();
        final Range<Integer> r4 = new Range<Integer>(minFat,maxFat);
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        Call<List<Food>> cb = test.getFoods(new QueryWrapper().getOptions());

        cb.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                 ArrayList<Food> foodList = (ArrayList<Food>) response.body();
                for (int i = 0;i<foodList.size();i++) {
                    Food f = foodList.get(i);
                    System.out.println(f.getName());
                   /* if(!r1.contains(f.getTotalEnergy()) || !r2.contains(f.getTotalPro()) || !r3.contains(f.getTotalCab()) || !r4.contains(f.getTotalFat())){
                        foodList.remove(i); i--;
                    }else{*/
                    ArrayList<Inclusion> listOfIngredients = (ArrayList<Inclusion>) f.getInclusions();
                    for (int j = 0; j < listOfIngredients.size(); j++) {
                        if (allergic.contains(listOfIngredients.get(j).getName())) {
                            j = listOfIngredients.size();
                            foodList.remove(i);
                            i--;
                        }
                    }
                }
                    //}
                   final FoodsAdapter adapter = new FoodsAdapter(FoodSearchFragment.this.getContext(), foodList);
                    setListAdapter(adapter);
    /*                tvName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(adapter.isSorted()){
                                Collections.sort(foodList,Food.czToA);
                                adapter.setSorted(false);
                                tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                            }else {
                                Collections.sort(foodList,Food.caToZ);
                                adapter.setSorted(true);
                                tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                            }
                            adapter.setFoods(foodList);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    tvRating.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(adapter.isSorted()){
                                Collections.sort(foodList,Food.czToARating);
                                adapter.setSorted(false);
                                tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                            }else {
                                Collections.sort(foodList,Food.caToZRating);
                                adapter.setSorted(true);
                                tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                            }
                            adapter.setFoods(foodList);
                            adapter.notifyDataSetChanged();
                        }
                    });*/
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                System.out.println("HATA VAR");


            }
        });
    }
    private void getAllIngredients(){
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        Call<List<Ingredient>> cb = test.getIngredients(queryWrapper.getOptions());
        cb.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                IngredientAdapter adapter = new IngredientAdapter(FoodSearchFragment.this.getContext(), (ArrayList<Ingredient>) response.body());
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setItemsCanFocus(false);
                lv.setAdapter(adapter);

                mtext1.setAdapter(adapter);
                mtext1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
    }
}