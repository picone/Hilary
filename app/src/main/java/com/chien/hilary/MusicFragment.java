package com.chien.hilary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 音乐播放的Fragment
 */
public class MusicFragment extends Fragment{

    public MusicFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_music,container,false);
        view.findViewById(R.id.last_page).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).switchFragment(4);
            }
        });
        view.findViewById(R.id.next_page).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).switchFragment(6);
            }
        });
        return view;
    }
}
