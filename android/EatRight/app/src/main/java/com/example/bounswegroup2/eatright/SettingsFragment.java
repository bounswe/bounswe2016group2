package com.example.bounswegroup2.eatright;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;

import com.example.bounswegroup2.Models.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private ListView lv;
    private MultiAutoCompleteTextView mtext1;
    private Button saveBut;
    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_settings, container, false);
        lv = (ListView)rootView.findViewById(R.id.ingr_listview_settings);
        mtext1 = (MultiAutoCompleteTextView)rootView.findViewById(R.id.mAutoTVForSettings);
        saveBut = (Button)rootView.findViewById(R.id.button_save_settings);
        return rootView;
    }
    public void onActivityCreated(Bundle savedInstancesState) {
        //onResume happens after onStart and onActivityCreate
        super.onActivityCreated(savedInstancesState);
        //getAllIngredients();
        saveBut.setOnClickListener(saveButClicked());
        //IngredientAdapter adapter = new IngredientAdapter(SettingsFragment.this.getContext(), null);
      //  mtext1.setAdapter(adapter);
       // mtext1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private View.OnClickListener saveButClicked() {
    return new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            List<String> items = Arrays.asList(mtext1.getText().toString().split("\\s*,\\s*"));
            for (String s:items) System.out.println(s);
        }
    };
    }


}
