package com.chien.hilary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 第一页的Fragment
 */
public class FirstFragment extends Fragment{

    public FirstFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_first,container,false);
        view.findViewById(R.id.next_page).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).switchFragment(1);
            }
        });
        return view;
    }
}