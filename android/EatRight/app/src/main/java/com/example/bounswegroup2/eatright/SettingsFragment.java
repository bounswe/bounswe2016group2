package com.example.bounswegroup2.eatright;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Utils.ApiInterface;

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
    private String query;
    private EditText allergicEditText;
    private Button searchButton;
    public void setArgs(String param1){
        Bundle args = new Bundle();
        args.putString("PARAM1",param1);
        this.setArguments(args);
    }
    public SettingsFragment() {
        // Required empty public constructor
        query = "";
    }


    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_settings, container, false);
        allergicEditText = (EditText)rootView.findViewById(R.id.alergic_edit_text);
        searchButton = (Button)rootView.findViewById(R.id.searchFood);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = allergicEditText.getText().toString();
                getAllFoods(query,s);
            }
        });
        return  rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstancesState) {
        //onResume happens after onStart and onActivityCreate
        super.onActivityCreated(savedInstancesState);
        //getAllFoods(query);
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

    private void getAllFoods(String queryString, final String allergic){
        if(queryString == null) queryString = "";
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        Call<List<Food>> cb = test.getFoodsWithQuery(queryString);
        cb.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                ArrayList<Food> foodList = (ArrayList<Food>) response.body();
                for (int i = 0;i<foodList.size();i++){
                    Food f = foodList.get(i);
                    ArrayList<Ingredient> listOfIngredients = f.getIngredients();
                    for (int j=0;j<listOfIngredients.size();j++){
                        if (listOfIngredients.get(j).getName().equalsIgnoreCase(allergic)){
                            j = listOfIngredients.size();
                            foodList.remove(i);
                            i--;
                        }
                    }

                }
                FoodsAdapter adapter = new FoodsAdapter(SettingsFragment.this.getContext(), foodList);
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
