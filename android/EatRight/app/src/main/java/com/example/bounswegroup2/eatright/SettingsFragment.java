package com.example.bounswegroup2.eatright;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Ingredient;
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
public class SettingsFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private String query;
    private EditText allergicEditText;
    private Button searchButton;
    private ListView lv;
    private MultiAutoCompleteTextView mtext1;
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
                List<String> items = Arrays.asList(mtext1.getText().toString().split("\\s*,\\s*"));
                getAllFoods(query,items);
            }
        });
        lv = (ListView) rootView.findViewById(R.id.ingr_listview);
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
        getListView().setDivider(ContextCompat.getDrawable(SettingsFragment.this.getContext(),android.R.color.black));
        getListView().setDividerHeight(1);
        //setRetainInstance(true);
    }

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        if(adapter.getId() == getListView().getId()){
            ViewGroup viewGroup = (ViewGroup)view;
            Food food = (Food)adapter.getAdapter().getItem(position);

            TextView textView = (TextView) viewGroup.findViewById(R.id.food_name);
            Toast.makeText(getActivity(),textView.getText().toString()+"\n"+ Arrays.toString(food.getIngredients().toArray()),Toast.LENGTH_LONG).show();
        }
       /* if(adapter.getId() == lv.getId()){
            SparseBooleanArray clickedItemPositions = lv.getCheckedItemPositions();
            String s ="";
            for(int index=0;index<clickedItemPositions.size();index++){
                // Get the checked status of the current item
                boolean checked = clickedItemPositions.valueAt(index);

                if(checked){
                    // If the current item is checked
                    int key = clickedItemPositions.keyAt(index);
                    Ingredient item = (Ingredient) lv.getItemAtPosition(key);
                    s+=item.getName()+" ";
                    // Display the checked items on TextView
                    //mTextView.setText(mTextView.getText() + item + " | ");
                }
            }
           // ViewGroup viewGroup = (ViewGroup)view;
          //  Ingredient ingr = (Ingredient) adapter.getAdapter().getItem(position);

            //TextView textView = (TextView) viewGroup.findViewById(R.id.ingr_name);
            Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
        }*/
    }
    /*
    public void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l,v,position,id);

    }*/

    private void getAllFoods(String queryString, final List<String> allergic){
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
                        if (allergic.contains(listOfIngredients.get(j).getName())){
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
    private void getAllIngredients(){
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        Call<List<Ingredient>> cb = test.getIngredients(queryWrapper.getOptions());
        cb.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                IngredientAdapter adapter = new IngredientAdapter(SettingsFragment.this.getContext(), (ArrayList<Ingredient>) response.body());
                //lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                //lv.setItemsCanFocus(false);
                //lv.setAdapter(adapter);
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
