package com.chien.hilary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
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
                Color.BLUE,
                Color.DKGRAY
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
        int x=(int)(event.getX()/star_width);
        int y=(int)(event.getY()/star_height);
        if(event.getAction()==MotionEvent.ACTION_DOWN&&x<COL&&y<ROW&&stars[y][x]>=0){
            ArrayList<Point> same=new ArrayList<>();
            same.add(new Point(x,y));
            for(int i=0;i<same.size();i++){
                judge(same.get(i),same);
            }
            if(same.size()>1){
                int i=same.size();
                while(i-->0){
                    stars[same.get(i).y][same.get(i).x]=-1;
                }
                for(i=0;i<COL;i++){
                    for(int j=1;j<ROW;j++){
                        if(stars[j][i]==-1){
                            for(int k=j;k>0;k--){
                                stars[k][i]=stars[k-1][i];
                            }
                            stars[0][i]=-1;
                        }
                    }
                }
                int move_num=0;
                for(i=0;i<COL-move_num;){
                    if(stars[ROW-1][i]==-1){//如果是空列的话
                        for(int j=i+1;j<COL;j++){//后面的列前移
                            for(int k=ROW-1;k>=0&&stars[k][j]>=0;k--){
                                stars[k][j-1]=stars[k][j];
                                stars[k][j]=-1;
                            }
                        }
                        move_num++;
                    }else{
                        i++;
                    }
                }
                redraw();
            }
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
                    }else{
                        paint.setColor(Color.BLACK);
                    }
                    canvas.drawRect(j*star_width,i*star_height,(j+1)*star_width,(i+1)*star_height,paint);
                }
            }
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void judge(Point p,ArrayList<Point> same){
        if(p.y>0&&stars[p.y][p.x]==stars[p.y-1][p.x]){
            addList(new Point(p.x,p.y-1),same);
        }
        if(p.x>0&&stars[p.y][p.x]==stars[p.y][p.x-1]){
            addList(new Point(p.x-1,p.y),same);
        }
        if(p.y+1<ROW&&stars[p.y][p.x]==stars[p.y+1][p.x]){
            addList(new Point(p.x,p.y+1),same);
        }
        if(p.x+1<COL&&stars[p.y][p.x]==stars[p.y][p.x+1]){
            addList(new Point(p.x+1,p.y),same);
        }
    }

    private void addList(Point data,ArrayList<Point> list){
        int i=list.size();
        boolean exists=false;
        while(i-->0){
            if(list.get(i).equals(data)){
                exists=true;
                break;
            }
        }
        if(!exists)list.add(data);
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