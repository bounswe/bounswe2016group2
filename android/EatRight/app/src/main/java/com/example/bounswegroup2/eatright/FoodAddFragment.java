package com.example.bounswegroup2.eatright;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;

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
    private LinearLayout mLayout;
    private AutoCompleteTextView mAutoCompleteTextView;
    private Button mButton;
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
                mLayout.addView(createNewTextView(mAutoCompleteTextView.getText().toString()));
            }
        };
    }

    private AutoCompleteTextView createNewTextView(String text) {
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final AutoCompleteTextView textView = new AutoCompleteTextView(this.getContext());
        textView.setLayoutParams(lparams);
        textView.setText(text);
        return textView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_food_add, container, false);
        mLayout = (LinearLayout) rootView.findViewById(R.id.linearLayoutAddFood);
        mAutoCompleteTextView = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextViewIngr);
        mButton = (Button) rootView.findViewById(R.id.buttonAdd);
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
        Ingredient i  = new Ingredient("bread","bread"); i.setCarb(100);i.setFat(100);i.setProtein(100); i.setEnergy(300);
        Ingredient i2 = new Ingredient("egg","egg"); i2.setCarb(200);i2.setFat(200);i2.setProtein(200); i2.setEnergy(600);
        Ingredient i3 = new Ingredient("eggplant","eggplant"); i3.setCarb(300);i3.setFat(300);i3.setProtein(300); i3.setEnergy(900);
        ArrayList<Ingredient> ingList = new ArrayList<Ingredient>(); ingList.add(i);ingList.add(i2);ingList.add(i3);
        IngredientAdapter adapter = new IngredientAdapter(FoodAddFragment.this.getContext(), ingList);
        mAutoCompleteTextView.setAdapter(adapter);
    }

}
