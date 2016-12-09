package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.bounswegroup2.Models.Ingredient;
import com.example.bounswegroup2.Utils.ApiInterface;
import com.example.bounswegroup2.Utils.QueryWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
    private Button mButton;
    private Button mButtonSubmit;
    private IngredientAdapter adapter;
    private ImageButton removeRowBut;
    private IngredientAdapter adapter2;
    private ArrayList<Ingredient> listOfIngr ;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
                    EditText et = (EditText) createNewEditTextView();
                    tr.addView(createNewTextView(et));
                    tr.addView(et);
                    tr.addView(createNewImageBut(et));
/* Add row to TableLayout. */
//tr.setBackgroundResource(R.drawable.sf_gradient_03);
                    mLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
                }else {
                    for (int i = 0; i < mLayout.getChildCount(); i++) {
                        View child = mLayout.getChildAt(i);

                        if (child instanceof TableRow) {
                            TableRow row = (TableRow) child;
                            int j = row.getChildCount();
                            for (int x = 0; x < j; x+=2) {
                                AutoCompleteTextView tView = (AutoCompleteTextView) row.getChildAt(x);
                                String s =tView.getText().toString();
                                EditText eView = (EditText) row.getChildAt(x+1);
                                System.out.println(s+" "+eView.getText().toString());
                            }
                        }
                    }
                }
            }
        };
    }

    private View createNewEditTextView() {
        final TableRow.LayoutParams lparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        final EditText eText = new EditText(this.getContext());
        eText.setHint(R.string.amount);
        eText.setEms(4);
        eText.setLayoutParams(lparams);
        return eText;
    }

    private ImageButton createNewImageBut(final EditText et){
        final TableRow.LayoutParams lparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        final ImageButton iBut = new ImageButton(this.getContext());
        iBut.setImageResource(android.R.drawable.ic_delete);
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
            }
        });

        return iBut;
    }

    private AutoCompleteTextView createNewTextView(final EditText et) {
        final TableRow.LayoutParams lparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        final AutoCompleteTextView textView = new AutoCompleteTextView(this.getContext());
        textView.setEms(11);
        textView.setHint(R.string.add_ingredient);
        textView.setLayoutParams(lparams);
        textView.setAdapter(adapter);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ingredient ingr = (Ingredient) adapterView.getAdapter().getItem(i);
                et.setHint("in unit of  "+ingr.getDefaultUnit());
                System.out.println(ingr.getMeasureUnit()+" "+ingr.getDefaultUnit());
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
        mAutoCompleteTextView = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextViewIngr);
        mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Ingredient ingr = (Ingredient) adapterView.getAdapter().getItem(i);
                amounEditTExt.setHint("in unit of  "+ingr.getDefaultUnit());
                System.out.println(ingr.getMeasureUnit()+" "+ingr.getDefaultUnit());

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
            }
        });
        mButton = (Button) rootView.findViewById(R.id.buttonAdd);
        mButtonSubmit = (Button) rootView.findViewById(R.id.addFoodSubmitBut);
        mButtonSubmit.setOnClickListener(buttonClicked());
        mButton.setOnClickListener(buttonClicked());
        return  rootView;
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
