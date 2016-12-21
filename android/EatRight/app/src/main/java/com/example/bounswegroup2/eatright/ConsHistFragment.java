package com.example.bounswegroup2.eatright;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bounswegroup2.Models.AteFoodUserless;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.TotalUserHistory;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.SessionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsHistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsHistFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View headerView;
    private TextView tvName;
    private TextView tvRating;
    private ListView myFoodLv;
    private ArrayList<Food> lof = new ArrayList<>();
    private Button bringFood;


    /**
     * Instantiates a new Cons hist fragment.
     */
    public ConsHistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsHistFragment.
     */
// TODO: Rename and change types and number of parameters
    public static ConsHistFragment newInstance(String param1, String param2) {
        ConsHistFragment fragment = new ConsHistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the diet_raw_layout for this fragment
        Log.d("CONS",mParam1);
        Log.d("CONS",mParam2);
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_cons_hist, container, false);
        myFoodLv = (ListView) rootView.findViewById(R.id.list_my_hist);
        headerView = inflater.inflate(R.layout.food_list_header,null);
        tvName = (TextView) headerView.findViewById(R.id.food_list_header_name);
        tvRating = (TextView) headerView.findViewById(R.id.food_list_header_rating);
        headerView.findViewById(R.id.food_list_header_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        bringFood = (Button)rootView.findViewById(R.id.bringMyHist);
        bringFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FoodsAdapter adapter = new FoodsAdapter(ConsHistFragment.this.getContext(), lof);
                myFoodLv.addHeaderView(headerView);
                myFoodLv.setAdapter(adapter);
                myFoodLv.setOnItemClickListener(ıtemClcked());
                myFoodLv.setDivider(ContextCompat.getDrawable(ConsHistFragment.this.getContext(),android.R.color.black));
                myFoodLv.setDividerHeight(1);
                tvName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(adapter.isSorted()){
                            Collections.sort(lof,Food.czToA);
                            adapter.setSorted(false);
                            tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                        }else {
                            Collections.sort(lof,Food.caToZ);
                            adapter.setSorted(true);
                            tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                        }
                        adapter.setFoods(lof);
                        adapter.notifyDataSetChanged();
                    }
                });
                tvRating.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(adapter.isSorted()){
                            Collections.sort(lof,Food.czToARating);
                            adapter.setSorted(false);
                            tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                        }else {
                            Collections.sort(lof,Food.caToZRating);
                            adapter.setSorted(true);
                            tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                        }
                        adapter.setFoods(lof);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
        return rootView;
    }

    private void fillMyFoodList() {
        String token = "Token " + SessionManager.getPreferences(getContext(),"token");
        ApiInterface[] test = {ApiInterface.retrofit.create(ApiInterface.class)};
        Call<TotalUserHistory> cb = test[0].getuserFoodHistory(token);
        cb.enqueue(new Callback<TotalUserHistory>() {
            @Override
            public void onResponse(Call<TotalUserHistory> call, Response<TotalUserHistory> response) {
                if(response.isSuccessful()){
                    TotalUserHistory userHistory = response.body();
                    ArrayList<AteFoodUserless> foodList = (ArrayList<AteFoodUserless>) userHistory.getTotal().getAteFoods();
                    System.out.println(foodList.size() + " HOOOOOOOOOOOOO12345676");
                    for(AteFoodUserless food : foodList){
                        lof.add(food.getFood());
                    }
                    System.out.println(lof.size());
                }
            }

            @Override
            public void onFailure(Call<TotalUserHistory> call, Throwable t) {
                System.out.println(t.getCause());
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstancesState) {
        //onResume happens after onStart and onActivityCreate
        super.onActivityCreated(savedInstancesState);
            fillMyFoodList();

    }

    private AdapterView.OnItemClickListener ıtemClcked() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Food food = (Food)adapterView.getAdapter().getItem(i);
                Bundle b = new Bundle();
                b.putSerializable("details",food.getDetails());
                b.putSerializable("ingr", (Serializable) food.getIngredients());
                b.putSerializable("name",food.getName());
                b.putSerializable("photo",food.getPhoto());
                if (food.getRestaurant() != null){
                    b.putSerializable("restaName",food.getRestaurant().getName());
                    b.putSerializable("restaID",food.getRestaurant().getId());
                }else if(food.getUser() != null){
                    b.putSerializable("restaName",food.getUser().getUsername());
                    b.putSerializable("restaID",0);
                }else{
                    b.putSerializable("restaName","Anonim");
                    b.putSerializable("restaID",0);
                }
                b.putSerializable("foodid",food.getId());
                b.putSerializable("rate",food.getRate());
                b.putSerializable("comments", (Serializable) food.getComments());
                Intent intent = new Intent(getActivity(), FoodPageActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        };
    }

}
