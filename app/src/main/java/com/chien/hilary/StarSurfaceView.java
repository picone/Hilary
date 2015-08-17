package com.chien.hilary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class StarSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    public final static int COL=8;
    public final static int ROW=12;

    private int star_width,star_height;

    private int stars[][];
    private int colors[];

    public StarSurfaceView(Context context,AttributeSet attrs){
        super(context,attrs);
        getHolder().addCallback(this);

        stars=new int[ROW][COL];
        colors=new int[]{
                Color.GREEN,
                Color.RED,
                Color.YELLOW,
                Color.WHITE,
                Color.BLUE
        };
        Random rnd=new Random();
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                stars[i][j]=rnd.nextInt(colors.length);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        //https://github.com/mlxy/Poping/blob/master/Poping/src/com/mlxy/poping/GameSurfaceView.java
        int x=(int)(event.getX()/star_width);
        int y=(int)(event.getY()/star_height);
        if(stars[y][x]>=0){

            return true;
        }else{
            return super.onTouchEvent(event);
        }
    }

    private void redraw(){
        Canvas canvas=getHolder().lockCanvas();
        if(canvas!=null){
            Paint paint=new Paint();
            for(int i=0;i<ROW;i++){
                for(int j=0;j<COL;j++){
                    if(stars[i][j]>=0){
                        paint.setColor(colors[stars[i][j]]);
                        canvas.drawRect(j*star_width,i*star_height,(j+1)*star_width,(i+1)*star_height,paint);
                    }
                }
            }
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        Rect v=holder.getSurfaceFrame();
        star_width=v.width()/COL;
        star_height=v.height()/ROW;
        redraw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){}
}