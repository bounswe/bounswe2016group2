package com.example.bounswegroup2.eatright;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bounswegroup2.Models.AteFoodUserless;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodTag;
import com.example.bounswegroup2.Models.TotalUserHistory;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;
import com.example.bounswegroup2.Utils.SessionManager;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.drawable.arrow_down_float;
import static android.R.drawable.arrow_up_float;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link recommendationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recommendationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View headerView;
    private TextView tvName;
    private TextView tvRating;
    private ListView myFoodLv;
    private FoodsAdapter adapter;
    private ArrayList<Food> lof = new ArrayList<>();
    ArrayList<String> tagList = new ArrayList<>();
    ArrayList<Food> foodies = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String ARG_POSITION = "position";
    private int position;


    public recommendationFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(int position) {
        recommendationFragment frag = new recommendationFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        frag.setArguments(b);
        return frag;
    }

    private void initiateFoodSearch(){
        String token = "Token " + SessionManager.getPreferences(getContext(),"token");
        ApiInterface[] test = {ApiInterface.retrofit.create(ApiInterface.class)};
        Call<TotalUserHistory> cb = test[0].getuserFoodHistory(token);
        cb.enqueue(new Callback<TotalUserHistory>() {
            @Override
            public void onResponse(Call<TotalUserHistory> call, Response<TotalUserHistory> response) {
                if(response.isSuccessful()){
                    TotalUserHistory userHistory = response.body();
                    ArrayList<AteFoodUserless> foodList = (ArrayList<AteFoodUserless>) userHistory.getTotal().getAteFoods();

                    for (AteFoodUserless ate : foodList){
                        List<FoodTag> tags = new ArrayList<FoodTag>();
                        tags = ate.getFood().getTags();
                        for (int i = 0; i < tags.size(); i++) {
                            tagList.add(tags.get(i).getName());
                            System.out.println("MOGO- " + tags.get(i).getName());
                        }
                    }
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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recommendation, container, false);
        position = getArguments().getInt(ARG_POSITION);
        myFoodLv = (ListView) rootView.findViewById(R.id.list_my_recom);
        headerView = inflater.inflate(R.layout.food_list_header,null);
        tvName = (TextView) headerView.findViewById(R.id.food_list_header_name);
        tvRating = (TextView) headerView.findViewById(R.id.food_list_header_rating);
        headerView.findViewById(R.id.food_list_header_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });
        adapter = new FoodsAdapter(recommendationFragment.this.getContext(), foodies);
        myFoodLv.addHeaderView(headerView);
        myFoodLv.setAdapter(adapter);
        myFoodLv.setOnItemClickListener(ıtemClcked());
        myFoodLv.setDivider(ContextCompat.getDrawable(recommendationFragment.this.getContext(),android.R.color.black));
        myFoodLv.setDividerHeight(1);
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.isSorted()){
                    Collections.sort(foodies,Food.czToA);
                    adapter.setSorted(false);
                    tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                }else {
                    Collections.sort(foodies,Food.caToZ);
                    adapter.setSorted(true);
                    tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                }
                adapter.setFoods(foodies);
                adapter.notifyDataSetChanged();
            }
        });
        tvRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.isSorted()){
                    Collections.sort(foodies,Food.czToARating);
                    adapter.setSorted(false);
                    tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_down_float,0);
                }else {
                    Collections.sort(foodies,Food.caToZRating);
                    adapter.setSorted(true);
                    tvRating.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,arrow_up_float,0);
                }
                adapter.setFoods(foodies);
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstancesState) {
        //onResume happens after onStart and onActivityCreate
        super.onActivityCreated(savedInstancesState);
        initiateFoodSearch();
        fillMyFoodList();

    }



    private void fillMyFoodList() {
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("tag",tagList);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm)).toString());
        Call<List<Food>> cb = test.searchFood(Constants.API_KEY,body);
        cb.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                foodies = (ArrayList<Food>) response.body();
                System.out.println(foodies);
                System.out.println("FOOOOOODIIIIES "+ foodies.size());
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

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
