package com.chien.hilary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 消星星那个Fragment
 */
public class StarFragment extends Fragment{

    public StarFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_star,container,false);
        view.findViewById(R.id.last_page).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).switchFragment(3);
            }
        });
        view.findViewById(R.id.next_page).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).switchFragment(5);
            }
        });
        return view;
    }
}
