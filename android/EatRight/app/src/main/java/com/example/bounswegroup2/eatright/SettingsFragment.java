package com.example.bounswegroup2.eatright;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_settings, container, false);
        String s = getArguments().getString("PARAM1");
        String [] dataSource = {"Eng","Spa","Fra","Tr",""};
        dataSource[4] = s;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.listlayout,R.id.txtitem,dataSource);
        setListAdapter(adapter);
        setRetainInstance(true);
        return  rootView;
    }
    public void onListItemClick(ListView l,View v,int position,long id){
        ViewGroup viewGroup = (ViewGroup)v;
        TextView textView = (TextView) viewGroup.findViewById(R.id.txtitem);
        Toast.makeText(getActivity(),textView.getText().toString(),Toast.LENGTH_LONG).show();
    }

}
