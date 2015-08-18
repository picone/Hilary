package com.chien.hilary;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 音乐播放的Fragment
 */
public class MusicFragment extends Fragment{

    private MediaPlayer mp;
    private Button command;

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
        command=(Button)view.findViewById(R.id.command);
        return view;
    }

    @Override
    public void onViewCreated(View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        mp=MediaPlayer.create(getActivity().getApplicationContext(),R.raw.music);
        mp.setLooping(true);
        command.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mp.isPlaying()){
                    mp.pause();
                    command.setText(R.string.play);
                }else{
                    mp.start();
                    command.setText(R.string.stop);
                }
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mp.release();
    }
}
