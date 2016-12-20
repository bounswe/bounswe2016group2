package com.example.bounswegroup2.eatright;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bounswegroup2.Models.Diet;
import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Models.Tag;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;
import com.example.bounswegroup2.Utils.QueryWrapper;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private Button saveBut;
    private ListView lv;
    private MultiAutoCompleteTextView mtext1;
    private RangeSeekBar<Integer> calorieSeekBar;
    private RangeSeekBar<Integer> proSeekBar;
    private RangeSeekBar<Integer> fatSeekBar;
    private RangeSeekBar<Integer> carbSeekBar;
    private ArrayList<Integer> ingIds = new ArrayList<>();
    private IngredientAdapter ingAdap;
    private EditText name;
    private EditText descr;
    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_settings, container, false);
        // Inflate the layout for this fragment


        lv = (ListView) rootView.findViewById(R.id.ingr_listviewSett);
        calorieSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekbarForCalorieSett);
        calorieSeekBar.setOnRangeSeekBarChangeListener(seekBarChnaged());
        fatSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekBarForFatSett);
        fatSeekBar.setOnRangeSeekBarChangeListener(seekBarChnaged());
        proSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.rangeSeekBarForProSett);
        proSeekBar.setOnRangeSeekBarChangeListener(seekBarChnaged());
        carbSeekBar = (RangeSeekBar<Integer>) rootView.findViewById(R.id.seekBarForCabSett);
        carbSeekBar.setOnRangeSeekBarChangeListener(seekBarChnaged());
        mtext1 = (MultiAutoCompleteTextView)rootView.findViewById(R.id.multiAutoCompleteTextView1Sett);
        saveBut = (Button) rootView.findViewById(R.id.saveDietSett);
        name = (EditText) rootView.findViewById(R.id.editTextNameSett);
        descr = (EditText)rootView.findViewById(R.id.editTextDescSett);
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

    public void onActivityCreated(Bundle savedInstancesState) {
        //onResume happens after onStart and onActivityCreate
        super.onActivityCreated(savedInstancesState);

        getAllIngredients();
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                hm.put("ingredients",ingIds); hm.put("name",name.getText().toString());
                hm.put("description",descr.getText().toString());
                RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm)).toString());
                Call<Diet> cb = test.addDiet("Token "+Constants.API_KEY,body);
                cb.enqueue(new Callback<Diet>() {
                    @Override
                    public void onResponse(Call<Diet> call, Response<Diet> response) {
                        Diet d = response.body();
                        int id = d.getId();
                        ApiInterface test2 = ApiInterface.retrofit.create(ApiInterface.class);
                        Call<Void> cb2 = test2.addMyDiet("Token "+Constants.API_KEY,id);
                        cb2.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                String s = response.message().toString();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Diet> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void getAllIngredients() {
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        QueryWrapper queryWrapper = new QueryWrapper();
        Call<List<Ingredient>> cb = test.getIngredients(queryWrapper.getOptions());
        cb.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {

                ingAdap= new IngredientAdapter(SettingsFragment.this.getContext(),(ArrayList<Ingredient>) response.body());
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



}
