package com.example.bounswegroup2.eatright;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Range;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodLess;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.QueryWrapper;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private  ArrayList<Food> nFl = new ArrayList<Food>();
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
        headerView.findViewById(R.id.food_list_header_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        lv = (ListView) rootView.findViewById(R.id.ingr_listview);
        calorieSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekbarForCalorie);
        fatSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekBarForFat);
        proSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.rangeSeekBarForPro);
        carbSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekBarForCab);

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
            Bundle b = new Bundle();
            b.putSerializable("details",food.getDetails());
            b.putSerializable("ingr", (Serializable) food.getIngredients());
            b.putSerializable("name",food.getName());
            b.putSerializable("photo",food.getPhoto());
            b.putSerializable("resta",food.getRestaurant());
            b.putSerializable("foodid",food.getId());
            //b.putSerializable("rate",food.getRates().get(0));
            Intent intent = new Intent(getActivity(), FoodPageActivity.class);
            intent.putExtras(b);
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

        Call<List<FoodLess>> cb = test.getFoods();

        cb.enqueue(new Callback<List<FoodLess>>() {
            @Override
            public void onResponse(Call<List<FoodLess>> call, final Response<List<FoodLess>> response) {
                final ArrayList<FoodLess> foodList = (ArrayList<FoodLess>) response.body();
                for (FoodLess fls: foodList){
                    int id = fls.getId();
                    ApiInterface test2 = ApiInterface.retrofit.create(ApiInterface.class);
                    Call<Food> cbFood = test2.getFoodWithId(id);
                    cbFood.enqueue(new Callback<Food>() {
                        @Override
                        public void onResponse(Call<Food> call2, Response<Food> response2) {
                           Food f = response2.body();
                           nFl.add(f);
                        }

                        @Override
                        public void onFailure(Call<Food> call2, Throwable t2) {

                        }
                    });
                }

                   final FoodsAdapter adapter = new FoodsAdapter(FoodSearchFragment.this.getContext(), nFl);
                   setListAdapter(adapter);
                   tvName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(adapter.isSorted()){
                                Collections.sort(nFl,Food.czToA);
                                adapter.setSorted(false);
                                tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                            }else {
                                Collections.sort(nFl,Food.caToZ);
                                adapter.setSorted(true);
                                tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                            }
                            adapter.setFoods(nFl);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    tvRating.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(adapter.isSorted()){
                                Collections.sort(nFl,Food.czToARating);
                                adapter.setSorted(false);
                                tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                            }else {
                                Collections.sort(nFl,Food.caToZRating);
                                adapter.setSorted(true);
                                tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                            }
                            adapter.setFoods(nFl);
                            adapter.notifyDataSetChanged();
                        }
                    });
            }

            @Override
            public void onFailure(Call<List<FoodLess>> call, Throwable t) {
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