package com.chien.hilary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 主Activity
 */
public class MainActivity extends AppCompatActivity{

    private Fragment mFragment[];
    private int cur_position;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragment=new Fragment[7];
        mFragment[0]=new FirstFragment();

        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container,mFragment[0]);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //实例化一个菜单
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_exit:
                finish();
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void switchFragment(int position){
        if(position!=cur_position){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.hide(mFragment[cur_position]);
            if(mFragment[position]==null){
                switch(position){
                    case 1:
                        mFragment[1]=new SecondFragment();
                        break;
                    case 2:
                        mFragment[2]=new CodeFragment();
                        break;
                    case 3:
                        mFragment[3]=new WalkFragment();
                        break;
                    case 4:
                        mFragment[4]=new StarFragment();
                        break;
                    case 5:
                        mFragment[5]=new MusicFragment();
                        break;
                    case 6:
                        mFragment[6]=new EndFragment();
                        break;
                }
                ft.add(R.id.container,mFragment[position]);
            }else{
                ft.show(mFragment[position]);
            }
            ft.commit();
            cur_position=position;
        }
    }
}