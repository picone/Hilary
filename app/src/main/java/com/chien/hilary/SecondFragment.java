package com.chien.hilary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 第二页的Fragment
 */
public class SecondFragment extends Fragment{

    public SecondFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_second,container,false);
        view.findViewById(R.id.last_page).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).switchFragment(0);
            }
        });
        view.findViewById(R.id.next_page).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).switchFragment(2);
            }
        });
        return view;
    }

}
