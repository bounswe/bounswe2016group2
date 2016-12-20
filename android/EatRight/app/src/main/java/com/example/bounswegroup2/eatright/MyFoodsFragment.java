package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodLess;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;


/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link MyFoodsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFoodsFragment extends Fragment {
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
    public MyFoodsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFoodsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFoodsFragment newInstance(String param1, String param2) {
        MyFoodsFragment fragment = new MyFoodsFragment();
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
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_my_foods, container, false);
        myFoodLv = (ListView) rootView.findViewById(R.id.list_my_foods);
        headerView = inflater.inflate(R.layout.food_list_header,null);
        tvName = (TextView) headerView.findViewById(R.id.food_list_header_name);
        tvRating = (TextView) headerView.findViewById(R.id.food_list_header_rating);
        headerView.findViewById(R.id.food_list_header_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        bringFood = (Button)rootView.findViewById(R.id.bringMyFood);
        bringFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FoodsAdapter adapter = new FoodsAdapter(MyFoodsFragment.this.getContext(), lof);
                myFoodLv.setAdapter(adapter);
                myFoodLv.setOnItemClickListener(ıtemClcked());
                myFoodLv.addHeaderView(headerView);
                myFoodLv.setDivider(ContextCompat.getDrawable(MyFoodsFragment.this.getContext(),android.R.color.black));
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fillMyFoodList();
    }

    private void fillMyFoodList() {
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        Call<List<FoodLess>> cb = test.getMyFoods("Token "+Constants.API_KEY);
        cb.enqueue(new Callback<List<FoodLess>>() {
            @Override
            public void onResponse(Call<List<FoodLess>> call, Response<List<FoodLess>> response) {
                ArrayList<FoodLess> foodless = (ArrayList<FoodLess>) response.body();
                for (FoodLess fls : foodless) {
                    int id = fls.getId();
                    ApiInterface test2 = ApiInterface.retrofit.create(ApiInterface.class);
                    Call<Food> cb2 = test2.getFoodWithId(Constants.API_KEY, id);
                    cb2.enqueue(new Callback<Food>() {
                        @Override
                        public void onResponse(Call<Food> call, Response<Food> response) {
                            Food f = response.body();
                            lof.add(f);
                        }

                        @Override
                        public void onFailure(Call<Food> call, Throwable t) {

                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<FoodLess>> call, Throwable t) {

            }
        });


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
                }else{
                    b.putSerializable("restaName",Constants.user);
                    b.putSerializable("restaID",0);
                }
                b.putSerializable("foodid",food.getId());
                b.putSerializable("rate",food.getDetails().getRate());
                b.putSerializable("comments", (Serializable) food.getComments());
                Intent intent = new Intent(getActivity(), FoodPageActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        };
    }

}
