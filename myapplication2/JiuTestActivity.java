package com.example.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.example.myapplication2.chishi.GrtResult;
import com.example.myapplication2.chishi.RandomNums;

import com.example.myapplication2.gamebean.MyPoint;

import java.util.ArrayList;
import java.util.Collections;


public class JiuTestActivity extends Activity {
    //声明一个数组
    private TextView[] textViews;
    private MyPoint[] points;
    private  String TAG ="TAG";
    int widh;
    private TextView tvReslt;
    public  int[] ids={R.id.tv_0,R.id.tv_1,R.id.tv_2,R.id.tv_3,R.id.tv_4,R.id.tv_5,R.id.tv_6,R.id.tv_7,R.id.tv_8,};
    LinearLayout lvContent;

    private  static  final  int TV_ACTION_ACTIVE=0;
    private  static  final  int TV_ACTION_NOMAL=1;
    private  int reslutForTwo = 0;
    private  TextView tvReslt0 ;
    int i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiu_lay);
        initView();
        initPoint();
        setlisner();
    }
    private void initView()
    {
        textViews=new TextView[9];
        for (int i = 0; i <ids.length ; i++) {
            textViews[i]=findViewById(ids[i]);
            textViews[i].setTag(TV_ACTION_NOMAL);

        }
        tvReslt0 =findViewById(R.id.tv_res0);
        lvContent=findViewById(R.id.lv_content);
        tvReslt =findViewById(R.id.tv_reslut);
        getNewNums();
        /* panzuhua 2021 11 18 刷新九宫格 测试代码
         */

    }
    private void getNewNums(){
        RandomNums randomNums = new RandomNums();
        int[] data =   randomNums.getNums();
        reslutForTwo = GrtResult.get3Num(data);
        tvReslt0.setText(reslutForTwo+"");
        for (int i = 0; i < textViews.length; i++) {
            if(i%2==0){
                textViews[i].setText(data[i/2]+"");
            }

        }
    }







//i表示行j表示列,一行三个,宽高为 widh
    private void  initPoint(){
         widh=dip2px(this,120);
        points =new MyPoint[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                MyPoint point=new MyPoint (widh/2+j*widh,widh/2+i*widh);
                points[i*3+j]= point;

            }
        }
    }

    //触摸事件,设置lvcontent监听
    private  void  setlisner(){
        lvContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: "+motionEvent.getX()+">>>>>>>>>>>"+motionEvent.getY()+">>>>>>>>>>"+points[8].x+">>>>>>>>>>>>>"+points[8].y);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d(TAG, "onTouch: "+motionEvent.getX()+">>>>>>>>>>>"+motionEvent.getY());
                        int x=(int)motionEvent.getX();
                        int y=(int)motionEvent.getY();
                        ArrayList<Integer>set =new ArrayList<>();
                        ArrayList<Integer>set1=new ArrayList<>();
                        for (int i=0;i<9;i++)
                        {
                            int dis =(int) Math.sqrt((points[i].y-y)*(points[i].y-y)+(points[i].x-x)*(points[i].x-x));
                            set.add(dis);
                            set1.add(dis);
                        }
                        Collections.sort(set);
                        int minDis=set.get(0);
                        int seleti=-1;
                        for (int i = 0; i < 9; i++) {
                            if (set1.get(i)==minDis) {
                                seleti=i;
                            }
                            }
                        if (minDis>widh/2) {
                            return  true;
                        }
                        if((int)textViews[seleti].getTag()==TV_ACTION_ACTIVE)
                        {
                            return  true;
                        }
                        textViews[seleti].setBackgroundColor(Color.YELLOW);
                        textViews[seleti].setTag(TV_ACTION_ACTIVE);
                        tvReslt.append(textViews[seleti].getText().toString());

                        break;
                    case MotionEvent.ACTION_UP:

                        Log.d(TAG, "onTouch: "+motionEvent.getX()+">>>>>>>>>>>"+motionEvent.getY());
                        String str = tvReslt.getText().toString();
                        int useReslut = -1;
                        if(str.charAt(1)=='+'){
                            useReslut = Integer.parseInt(String.valueOf(str.charAt(0)))+Integer.parseInt(String.valueOf(str.charAt(2)));
                        }else if(str.charAt(1)=='-') {
                            useReslut = Integer.parseInt(String.valueOf(str.charAt(0)))-Integer.parseInt(String.valueOf(str.charAt(2)));
                        }else{
                            Toast.makeText(JiuTestActivity.this,"您输入的格式有误，请重新输入",Toast.LENGTH_SHORT).show();
                        }

                        tvReslt.setText("");
                        reSetBackG();
                        if(useReslut == reslutForTwo){
                            Toast.makeText(JiuTestActivity.this,"恭喜，游戏通关，即将进入下一关",Toast.LENGTH_SHORT).show();
                            getNewNums();
                        }else {
                            Toast.makeText(JiuTestActivity.this,"您的结果不对，请重新输入",Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });

        }
        public static  int dip2px(Context context,float dip)
        {
            final  float scale=context.getResources().getDisplayMetrics().density;
            return(int)(dip*scale+0.5);
        }
        public  void reSetBackG() {
            for (int i = 0; i < 9; i++) {
                textViews[i].setTag(TV_ACTION_NOMAL);
                if (i%2==0) {
                    textViews[i].setBackgroundColor(Color.WHITE);
                }
                else {
                    textViews[i].setBackgroundColor(Color.BLACK);
                }

            }


        }

    private void welcome () {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 0;

                while (true) {
                    for (i = 0; i < textViews.length; i++) {
                        try {
                            Thread.sleep(1000 * i);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    textViews[i].setBackgroundColor(Color.YELLOW);
                                }
                            });


                        } catch (Exception e) {

                        }

                    }

                }

            }
        });
        thread.start();


    }

}
