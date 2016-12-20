package com.example.bounswegroup2.eatright;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bounswegroup2.Models.Food;
import com.example.bounswegroup2.Models.FoodAddResponse;
import com.example.bounswegroup2.Models.FoodComment;
import com.example.bounswegroup2.Models.FoodLess;
import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Models.Tag;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.Constants;
import com.example.bounswegroup2.Utils.QueryWrapper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bounswegroup2.eatright.R.id.addFoodSubmitBut;
import static com.example.bounswegroup2.eatright.R.id.saveTags;
import static com.example.bounswegroup2.eatright.UserHomeActivity.*;


/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link FoodAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodAddFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TableLayout mLayout;
    private AutoCompleteTextView mAutoCompleteTextView;
    private EditText amounEditTExt;
    private EditText valueEditTExt;
    private TextView unitTV;
    private TextView valueTV;
    private Button mButton;
    private Button mButtonSubmit;
    private IngredientAdapter adapter;
    private ImageButton removeRowBut;
    private EditText descrpFood;
    private EditText nameFood;
    private int foodId = 0;
    private boolean aBoolean = true;
    // TODO: Rename and change types of parameters

    private boolean addColour = true;
    private EditText et;
    private EditText et2;
    private Button tagButt;
    private EditText tagET;
    private ListView tagLV;
    private ArrayList<Tag> lotags = new ArrayList<>();
    private ArrayList<String> lotagsNames = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public FoodAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FoodAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodAddFragment newInstance(String param1, String param2) {
        FoodAddFragment fragment = new FoodAddFragment();
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
           // mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
        }
        

    }

    private View.OnClickListener buttonClicked(){

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == mButton.getId()) {
                /* Create a new row to be added. */
                    TableRow tr = new TableRow(getContext());
                    tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
/* Add Button to row. */
                     et = (EditText) createNewEditTextView(); et.setTag("M");
                     et2 = (EditText) createNewEditTextView(); et2.setTag("V");
                    tr.addView(createNewTextView(et));
                    tr.addView(et);
                    tr.addView(createTV("UTV"));
                    tr.addView(et2);
                    tr.addView(createTV("VTV"));
                    tr.addView(createNewImageBut(et));
                    if(addColour)
                    tr.setBackgroundResource(android.R.color.holo_green_light);
                    et.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }
                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }
                        @Override
                        public void afterTextChanged(Editable editable) {
                            String s = et.getText().toString();
                            String s2 = et2.getText().toString();
                            if(et.isFocused() &&  !s.isEmpty() && !s2.isEmpty() ){
                                double v1 = Double.parseDouble(et.getHint().toString());
                                double v2 = Double.parseDouble(et2.getHint().toString());
                                double valMeasure = Double.parseDouble(s);
                                double newVal = (v2/v1)*valMeasure;
                                et2.setText(""+newVal);
                            }
                        }
                    });
                    et2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }
                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }
                        @Override
                        public void afterTextChanged(Editable editable) {
                            String s = et.getText().toString();
                            String s2 = et2.getText().toString();
                            if(et2.isFocused() && !s.isEmpty() && !s2.isEmpty()){
                                double v1 = Double.parseDouble(et.getHint().toString());
                                double v2 = Double.parseDouble(et2.getHint().toString());
                                double val = Double.parseDouble(s2);
                                double newVal = (v1/v2)*val;
                                et.setText(""+newVal);
                            }
                        }
                    });

                    addColour = !addColour;
                    mLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }else {
                    // This is when submit clicked
                    final HashMap<Integer,Double> ing = new HashMap<>();
                    for (int i = 0; i < mLayout.getChildCount(); i++) {
                        View child = mLayout.getChildAt(i);
                        if (child instanceof TableRow) {
                            TableRow row = (TableRow) child;
                            int j = row.getChildCount();
                            for (int x = 0; x < j; x+=6) {
                                AutoCompleteTextView tView = (AutoCompleteTextView) row.getChildAt(x);
                                String s = tView.getText().toString();
                               int id = adapter.getIngredient(s).getId();
                                EditText valueET = (EditText) row.getChildAt(x+3);
                                String s3 = valueET.getText().toString();
                                ing.put(id,Double.parseDouble(s3));
                            }
                        }
                    }
                    // food add
                    String desc = descrpFood.getText().toString();
                    String name = nameFood.getText().toString();
                    ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
                    HashMap<String,Object>hm = new HashMap<>();
                    hm.put("name",name);
                    hm.put("description",desc);
                    hm.put("tag",lotagsNames);
                    RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm)).toString());
                    Call<FoodLess> cb = test.addFood("Token "+ Constants.API_KEY,body);
                    cb.enqueue(new Callback<FoodLess>() {
                        @Override
                        public void onResponse(Call<FoodLess> call, Response<FoodLess> response) {
                            foodId = response.body().getId();
                            for(Map.Entry<Integer, Double> entry : ing.entrySet()) {
                                int id = entry.getKey();
                                double value = entry.getValue();
                                ApiInterface test2 = ApiInterface.retrofit.create(ApiInterface.class);
                                HashMap<String,Double>hm2 = new HashMap<>();
                                hm2.put("value",value);
                                RequestBody body2 = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(hm2)).toString());
                                Call<FoodAddResponse> cb2 = test2.addIngredientToFood("Token "+ Constants.API_KEY,foodId,id,body2);
                                cb2.enqueue(new Callback<FoodAddResponse>() {
                                    @Override
                                    public void onResponse(Call<FoodAddResponse> call, Response<FoodAddResponse> response) {
                                        FoodAddResponse far = response.body();
                                        if (far == null) aBoolean = false;

                                    }

                                    @Override
                                    public void onFailure(Call<FoodAddResponse> call, Throwable t) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<FoodLess> call, Throwable t) {

                        }
                    });

                    if (aBoolean){
                        Toast toast = Toast.makeText(getContext(),"You added your recipe successfully!",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                        toast.show();
                    }else{
                        Toast toast = Toast.makeText(getContext(),"Oops, something went wrong!",Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP|Gravity.CENTER,0,0);
                        toast.show();
                    }
                }
            }
        };
    }

    private View createTV(String tag) {
        final TableRow.LayoutParams lparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        final TextView eText = new TextView(this.getContext());
        lparams.weight = 1;
        eText.setLayoutParams(lparams);
        eText.setTag(tag);
        return eText;
    }

    private View createNewEditTextView() {
        final TableRow.LayoutParams lparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        final EditText eText = new EditText(this.getContext());
        lparams.weight = 2;
        eText.setLayoutParams(lparams);
        return eText;
    }

    private ImageButton createNewImageBut(final EditText et){
        final TableRow.LayoutParams lparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        final ImageButton iBut = new ImageButton(this.getContext());
        iBut.setImageResource(android.R.drawable.ic_delete);
        lparams.weight = 1;
        iBut.setLayoutParams(lparams);
        iBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // row is your row, the parent of the clicked button
                View row = (View) et.getParent();
                // container contains all the rows, you could keep a variable somewhere else to the container which you can refer to here
                ViewGroup container = ((ViewGroup)row.getParent());
                // delete the row and invalidate your view so it gets redrawn
                container.removeView(row);
                container.invalidate();
                addColour = !addColour;
            }
        });

        return iBut;
    }

    private AutoCompleteTextView createNewTextView(final EditText et) {
        final TableRow.LayoutParams lparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        final AutoCompleteTextView textView = new AutoCompleteTextView(this.getContext());
        lparams.weight = 10;
        textView.setHint(R.string.add_ingredient);
        textView.setLayoutParams(lparams);
        textView.setAdapter(adapter);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ingredient ingr = (Ingredient) adapterView.getAdapter().getItem(i);
                ViewGroup vg= (ViewGroup) textView.getParent();
                setETsForUnits(ingr.getMeasureUnit(),ingr.getMeasureValue(),ingr.getDefaultUnit(),ingr.getDefaultValue(),vg);
            }
        });
        return textView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_food_add, container, false);
        mLayout = (TableLayout) rootView.findViewById(R.id.tableLayoutAddFood);
        amounEditTExt = (EditText) rootView.findViewById(R.id.amountEText);
        descrpFood = (EditText) rootView.findViewById(R.id.foodDesc);
        nameFood = (EditText) rootView.findViewById(R.id.nameFood);
        tagButt = (Button) rootView.findViewById(R.id.addTagsButt);
        tagET = (EditText)rootView.findViewById(R.id.tagET);
        tagLV = (ListView)rootView.findViewById(R.id.listForTags);
        amounEditTExt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String s = amounEditTExt.getText().toString();
                String s2 = valueEditTExt.getText().toString();

                if(amounEditTExt.isFocused() &&  !s.isEmpty() && !s2.isEmpty() ){
                    double v1 = Double.parseDouble(amounEditTExt.getHint().toString());
                    double v2 = Double.parseDouble(valueEditTExt.getHint().toString());
                    double valMeasure = Double.parseDouble(s);
                    double newVal = (v2/v1)*valMeasure;
                    valueEditTExt.setText(""+newVal);
                }
            }
        });
        mAutoCompleteTextView = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextViewIngr);
        valueTV = (TextView) rootView.findViewById(R.id.valueTV);
        valueEditTExt = (EditText)rootView.findViewById(R.id.valueEText);
        valueEditTExt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String s = amounEditTExt.getText().toString();
                String s2 = valueEditTExt.getText().toString();

                if(valueEditTExt.isFocused() && !s.isEmpty() && !s2.isEmpty()){
                    double v1 = Double.parseDouble(amounEditTExt.getHint().toString());
                    double v2 = Double.parseDouble(valueEditTExt.getHint().toString());
                    double val = Double.parseDouble(s2);
                    double newVal = (v1/v2)*val;
                    amounEditTExt.setText(""+newVal);
                }
            }
        });
        unitTV = (TextView)rootView.findViewById(R.id.unitTV);
        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ingredient ingr = (Ingredient) adapterView.getAdapter().getItem(i);
                ViewGroup vg= (ViewGroup) valueEditTExt.getParent();
                setETsForUnits(ingr.getMeasureUnit(),ingr.getMeasureValue(),ingr.getDefaultUnit(),ingr.getDefaultValue(), vg);
            }
        });
        removeRowBut = (ImageButton) rootView.findViewById(R.id.removeRow);
        removeRowBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // row is your row, the parent of the clicked button
                View row = (View) view.getParent();
                // container contains all the rows, you could keep a variable somewhere else to the container which you can refer to here
                ViewGroup container = ((ViewGroup)row.getParent());
                // delete the row and invalidate your view so it gets redrawn
                container.removeView(row);
                container.invalidate();
                addColour = !addColour;
            }
        });
        mButton = (Button) rootView.findViewById(R.id.buttonAdd);
        mButtonSubmit = (Button) rootView.findViewById(R.id.addFoodSubmitBut);
        mButtonSubmit.setOnClickListener(buttonClicked());
        mButton.setOnClickListener(buttonClicked());
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return  rootView;
    }

    private void setETsForUnits(String measureUnit, Double measureValue, String defaultUnit, Double defaultValue, ViewGroup vg) {
        ((EditText)vg.findViewWithTag("M")).setText(measureValue.toString()); ((TextView)vg.findViewWithTag("UTV")).setText(measureUnit);
        ((EditText)vg.findViewWithTag("V")).setText(defaultValue.toString()); ((TextView)vg.findViewWithTag("VTV")).setText(defaultUnit);
        ((EditText)vg.findViewWithTag("M")).setHint(measureValue.toString()); ((EditText)vg.findViewWithTag("V")).setHint(defaultValue.toString());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onActivityCreated(Bundle savedInstancesState) {
        //onResume happens after onStart and onActivityCreate
        super.onActivityCreated(savedInstancesState);
        getAllIngredients();
        tagET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    populateTAgListView();
                }
            }
        });
        tagButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tagLV.setVisibility(View.GONE);
                Toast.makeText(FoodAddFragment.this.getContext(),"All Tags Saved",Toast.LENGTH_SHORT).show();
                mButtonSubmit.setVisibility(View.VISIBLE);
                tagButt.setVisibility(View.GONE);
                for (Tag t : lotags) lotagsNames.add(t.getName());
            }
        });
        tagLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tag tag = (Tag)adapterView.getAdapter().getItem(i);
                if (lotags.contains(tag)){
                    lotags.remove(tag);
                    Toast.makeText(FoodAddFragment.this.getContext(),"Removed Tag Succesfully:\n"+tag.getName(),Toast.LENGTH_SHORT).show();
                }else{
                    lotags.add(tag);
                    Toast.makeText(FoodAddFragment.this.getContext(),"Added Tag Succesfully:\n"+tag.getName(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void populateTAgListView() {
        String s = tagET.getText().toString();
        ApiInterface test = ApiInterface.retrofit.create(ApiInterface.class);
        Call<List<Tag>> cb = test.getTags("Token "+Constants.API_KEY,s);
        cb.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                ArrayList<Tag> lot = (ArrayList<Tag>) response.body();
                TagAdapter adp = new TagAdapter(FoodAddFragment.this.getContext(),lot);
                tagLV.setAdapter(adp);
                tagLV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                tagLV.setVisibility(View.VISIBLE);
                mButtonSubmit.setVisibility(View.GONE);
                tagButt.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {

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

                adapter = new IngredientAdapter(FoodAddFragment.this.getContext(), (ArrayList<Ingredient>) response.body());
                mAutoCompleteTextView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
            }
        });
    }

}
