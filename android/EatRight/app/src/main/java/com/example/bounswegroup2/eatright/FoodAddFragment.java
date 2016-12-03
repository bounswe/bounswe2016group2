package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.bounswegroup2.Models.Ingredient;

import java.util.ArrayList;


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
    private Button mButton;
    private Button mButtonSubmit;
    private IngredientAdapter adapter;
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
                    tr.addView(createNewTextView());
                    tr.addView(createNewEditTextView());
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
                                Ingredient ingredient = getIngredient(s);
                                EditText eView = (EditText) row.getChildAt(x+1);
                                System.out.println(ingredient.getEnergy().toString()+" "+eView.getText().toString());
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
        eText.setLayoutParams(lparams);
        return eText;
    }

    private AutoCompleteTextView createNewTextView() {
        final TableRow.LayoutParams lparams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        final AutoCompleteTextView textView = new AutoCompleteTextView(this.getContext());
        textView.setEms(8);
        textView.setHint(R.string.add_ingredient);
        textView.setLayoutParams(lparams);
        textView.setAdapter(adapter);
        return textView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_food_add, container, false);
        mLayout = (TableLayout) rootView.findViewById(R.id.tableLayoutAddFood);
        mAutoCompleteTextView = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextViewIngr);
        Ingredient i1  = new Ingredient("bread","bread"); i1.setCarb(100);i1.setFat(100);i1.setProtein(100); i1.setEnergy(300);
        Ingredient i2 = new Ingredient("egg","egg"); i2.setCarb(200);i2.setFat(200);i2.setProtein(200); i2.setEnergy(600);
        Ingredient i3 = new Ingredient("eggplant","eggplant"); i3.setCarb(300);i3.setFat(300);i3.setProtein(300); i3.setEnergy(900);
        ArrayList<Ingredient> ingList = new ArrayList<Ingredient>(); ingList.add(i1);ingList.add(i2);ingList.add(i3);
        listOfIngr = new ArrayList<Ingredient>();
        for (Ingredient i:ingList) listOfIngr.add(i);
        adapter = new IngredientAdapter(FoodAddFragment.this.getContext(), ingList);
        adapter2 = new IngredientAdapter(FoodAddFragment.this.getContext(), ingList);
        mAutoCompleteTextView.setAdapter(adapter);
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


    }

    @Nullable
    private Ingredient getIngredient(String name){
        for (Ingredient i: listOfIngr) {
            if (i.getName().equalsIgnoreCase(name)) return i;
        }
        return null;
    }

}
