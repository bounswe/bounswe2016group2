package com.example.bounswegroup2.eatright;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.QueryWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link ListFragment} subclass.
 */
public class SettingsFragment extends ListFragment {

    public void setArgs(String param1){
        Bundle args = new Bundle();
        args.putString("PARAM1",param1);
        this.setArguments(args);
    }
    public SettingsFragment() {
        // Required empty public constructor
    }


    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_settings, container, false);
        //*String s = getArguments().getString("PARAM1");
        //String [] dataSource = {"Eng","Spa","Fra","Tr",""};
        //dataSource[4] = s;*//*
        // Create the adapter to convert the array to views
            //ListView lv = (ListView)rootView.findViewById(R.id.list);


            // Attach the adapter to a ListView
           // ListView listView = (ListView)getListView();
           // listView.setAdapter(adapter);

        //setListAdapter(adapter);
        //setRetainInstance(true);

        return  rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstancesState) {
        //onResume happens after onStart and onActivityCreate
        super.onActivityCreated(savedInstancesState);
        getAllFoods();
        getListView().setDivider(ContextCompat.getDrawable(SettingsFragment.this.getContext(),android.R.color.black));
        getListView().setDividerHeight(1);
        //setRetainInstance(true);
    }

    public void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l,v,position,id);
        ViewGroup viewGroup = (ViewGroup)v;
        Food food = (Food)getListAdapter().getItem(position);

        TextView textView = (TextView) viewGroup.findViewById(R.id.food_name);
        Toast.makeText(getActivity(),textView.getText().toString()+"\n"+ Arrays.toString(food.getIngredients().toArray()),Toast.LENGTH_LONG).show();
    }

    private void getAllFoods(){

        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        QueryWrapper query = new QueryWrapper();
        Call<List<Food>> cb = test.getFoods(query.getOptions());
        cb.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                FoodsAdapter adapter = new FoodsAdapter(SettingsFragment.this.getContext(), (ArrayList<Food>)response.body());
                setListAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);

       /* searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getListAdapter();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });*/
    }
}
