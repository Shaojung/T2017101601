package com.example.teacher.t2017101601;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView);
    }
    public void click1(View v)
    {
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv1.setText("s:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                tv1.setText("done!");
            }
        }.start();
    }
}
