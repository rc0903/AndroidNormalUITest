package com.example.zgr.androidnormaluitest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zgr on 16/4/7.
 */
public class OtherActivity extends Activity implements ViewSwitcher.ViewFactory{


    TextSwitcher swit;
    Timer myTimer;
    TimerTask task;
    Handler myhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_main);

        result();

//        myTimer = new Timer();
//
//        myhandler = new Handler(){
//
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                if (msg.what == 1){
//                    String t = String.valueOf(new Date());
//                    swit.setText(t);
//                }
//            };
//
//        };
//
//
//
//        task = new TimerTask() {
//            @Override
//            public void run() {
//                Message message = new Message();
//                message.what = 1;
//                myhandler.handleMessage(message);
//            }
//        };

//        myTimer.schedule(task, 1000, 1000);


    selfHandler.postDelayed(runnable, 1000);
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   selfHandler.removeCallbacks(runnable);
            }
        });
    }


    Handler selfHandler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                selfHandler.postDelayed(this,1000);
                swit.setText(String.valueOf(new Date()));

            }catch (Exception e){
                System.out.println("exception " + e);

            }
        }
    };

    public  void result() {

        Intent t = getIntent();
        Bundle myBundle = t.getExtras();

        String str = "结果" + myBundle.get("myname") + String.valueOf(myBundle.getInt("age"));

        TextView text = (TextView) findViewById(R.id.showTextView);
        text.setText(str);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("返回上一页");
                OtherActivity.this.finish();
            }
        });

        swit = (TextSwitcher) findViewById(R.id.textSwitcher);
        swit.setFactory(this);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);

        swit.setInAnimation(in);
        swit.setOutAnimation(out);

        Button changeBtn = (Button) findViewById(R.id.changeNumber);
        if (changeBtn != null) {
            changeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String t = String.valueOf(new Date());
                    swit.setText(t);
                }
            });
        }


    }


    public  View makeView(){
        TextView tv = new TextView(this);
        tv.setTextSize(36);
        tv.setBackgroundColor(2);
        return tv;
    }
}
