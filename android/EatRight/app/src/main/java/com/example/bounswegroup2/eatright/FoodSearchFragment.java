package com.example.bounswegroup2.eatright;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
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
import android.widget.Toast;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Models.Tag;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;
import com.example.bounswegroup2.Utils.QueryWrapper;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
 * A simple {@link ListFragment} subclass.
 */
public class FoodSearchFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private String query;
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
    private ArrayList<Integer> ingIds = new ArrayList<>();
    private ArrayList<Food> nFl = new ArrayList<Food>();
    private ArrayList<String> selectedTags = new ArrayList<>();
    private EditText tagsET;
    private ListView tagLV;
    private Button saveTags;
    private ArrayList<Tag> lotags = new ArrayList<>();
    private ArrayList<String> lotagsNames = new ArrayList<>();

    /**
     * Set args.
     *
     * @param param1 the param 1
     */
    public void setArgs(String param1){
        Bundle args = new Bundle();
        args.putString("PARAM1",param1);
        this.setArguments(args);
    }

    /**
     * Instantiates a new Food search fragment.
     */
    public FoodSearchFragment() {
        // Required empty public constructor
        query = "";
    }


    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the diet_raw_layout for this fragment
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
        tagLV = (ListView) rootView.findViewById(R.id.tagListView);
        lv = (ListView) rootView.findViewById(R.id.ingr_listview);
        calorieSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekbarForCalorie);
        calorieSeekBar.setOnRangeSeekBarChangeListener(seekBarChnaged());
        fatSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekBarForFat);
        fatSeekBar.setOnRangeSeekBarChangeListener(seekBarChnaged());
        proSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.rangeSeekBarForPro);
        proSeekBar.setOnRangeSeekBarChangeListener(seekBarChnaged());
        carbSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekBarForCab);
        carbSeekBar.setOnRangeSeekBarChangeListener(seekBarChnaged());
        mtext1 = (MultiAutoCompleteTextView)rootView.findViewById(R.id.multiAutoCompleteTextView1);
        tagsET = (EditText) rootView.findViewById(R.id.editTextTag);
        saveTags = (Button) rootView.findViewById(R.id.saveTags);
        return  rootView;
    }

    private RangeSeekBar.OnRangeSeekBarChangeListener<Integer> seekBarChnaged() {
        return new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {

                Toast toast = Toast.makeText(getActivity(), String.valueOf("Min: "+bar.getSelectedMinValue()+"\nMax: "+
                bar.getSelectedMaxValue()),Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER|Gravity.BOTTOM,0,0);
                toast.show();
            }
        };
    }

    @Override
    public void onActivityCreated(Bundle savedInstancesState) {
        //onResume happens after onStart and onActivityCreate
        super.onActivityCreated(savedInstancesState);
        //getAllFoods(query)
        getListView().setOnItemClickListener(this);
        lv.setOnItemClickListener(this);
        tagsET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                populateTAgListView();
                }
            }
        });
        saveTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tagLV.setVisibility(View.GONE);
                Toast.makeText(FoodSearchFragment.this.getContext(),"All Tags Saved",Toast.LENGTH_SHORT).show();
                searchButton.setVisibility(View.VISIBLE);
                saveTags.setVisibility(View.GONE);
                for (Tag t : lotags) lotagsNames.add(t.getName());
            }
        });
        tagLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tag tag = (Tag)adapterView.getAdapter().getItem(i);
                if (lotags.contains(tag)){
                    lotags.remove(tag);
                    Toast.makeText(FoodSearchFragment.this.getContext(),"Removed Tag Succesfully:\n"+tag.getName(),Toast.LENGTH_SHORT).show();
                }else{
                    lotags.add(tag);
                    Toast.makeText(FoodSearchFragment.this.getContext(),"Added Tag Succesfully:\n"+tag.getName(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        getAllIngredients();
        getListView().addHeaderView(headerView);
        getListView().setDivider(ContextCompat.getDrawable(FoodSearchFragment.this.getContext(),android.R.color.black));
        getListView().setDividerHeight(1);
        //setRetainInstance(true);
    }

    private void populateTAgListView() {
        String s = tagsET.getText().toString();
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        Call<List<Tag>> cb = test.getTags("Token "+Constants.API_KEY,s);
        cb.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                ArrayList<Tag> lot = (ArrayList<Tag>) response.body();
                TagAdapter adp = new TagAdapter(FoodSearchFragment.this.getContext(),lot);
                tagLV.setAdapter(adp);
                tagLV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                tagLV.setVisibility(View.VISIBLE);
                getListView().setVisibility(View.INVISIBLE);
                searchButton.setVisibility(View.GONE);
                saveTags.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {

            }
        });
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
            if (food.getRestaurant() != null){
                b.putSerializable("restaName",food.getRestaurant().getName());
                b.putSerializable("restaID",food.getRestaurant().getId());
            }else{
                b.putSerializable("restaName",Constants.user);
                b.putSerializable("restaID",0);
            }
            b.putSerializable("foodid",food.getId());
            b.putSerializable("rate",food.getRate());
            b.putSerializable("comments", (Serializable) food.getComments());
            Intent intent = new Intent(getActivity(), FoodPageActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }
    }

    //Revise after server deployment
    private void getAllFoods(String queryString, final List<String> allergic){
        tagLV.setVisibility(View.INVISIBLE);
        getListView().setVisibility(View.VISIBLE);
        if(queryString == null) queryString = "";
        float minCalorie = calorieSeekBar.getSelectedMinValue();
        float maxCalorie = calorieSeekBar.getSelectedMaxValue();
        float minProtein = proSeekBar.getSelectedMinValue();
        float maxProtein = proSeekBar.getSelectedMaxValue();
        float minCarbon  = carbSeekBar.getSelectedMinValue();
        float maxCarbon  = carbSeekBar.getSelectedMaxValue();
        float minFat = fatSeekBar.getSelectedMinValue();
        float maxFat = fatSeekBar.getSelectedMaxValue();
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("minEnergy",  minCalorie); hm.put("maxEnergy",maxCalorie);
        hm.put("minProteinVal",  minProtein); hm.put("maxProteinVal",maxProtein);
        hm.put("minCarbVal", minCarbon); hm.put("maxCarbVal",maxCarbon);
        hm.put("minFatVal",  minFat); hm.put("maxFatVal",maxFat);
        hm.put("ingredients",ingIds); hm.put("tag",lotagsNames);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm)).toString());
        Call<List<Food>> cb = test.searchFood(Constants.API_KEY,body);
        cb.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
            nFl = (ArrayList<Food>) response.body();

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

                final IngredientAdapter ingAdap= new IngredientAdapter(FoodSearchFragment.this.getContext(),(ArrayList<Ingredient>) response.body());
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setItemsCanFocus(false);
                lv.setAdapter(ingAdap);

                mtext1.setAdapter(ingAdap);
                mtext1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                mtext1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Ingredient ing = (Ingredient)adapterView.getAdapter().getItem(i);
                        int id = ing.getId();
                        ingIds.add(id);
                    }
                });
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