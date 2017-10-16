package com.example.teacher.t2017101601;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4;
    Handler hander;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        hander = new Handler();
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

    public void click2(View v)
    {
        new Thread(){
            int i = 5;
            @Override
            public void run() {
                super.run();
                do {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv2.setText(String.valueOf(i));
                        }
                    });
                    i--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (i>=0);

            }
        }.start();
    }
    public void click3(View v)
    {
        new Thread(){
            int i = 5;
            @Override
            public void run() {
                super.run();
                do {
                    hander.post(new Runnable() {
                        @Override
                        public void run() {
                            tv3.setText(String.valueOf(i));
                        }
                    });
                    i--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (i>=0);

            }
        }.start();
    }
    public void click4(View v)
    {
        MyAsyncTask task = new MyAsyncTask(tv4);
        task.execute(5);
    }
}

class MyAsyncTask extends AsyncTask<Integer, Integer, String>
{
    TextView tv;
    public MyAsyncTask(TextView tv)
    {
        this.tv = tv;
    }

    @Override
    protected String doInBackground(Integer... integers) {
        int n = integers[0];
        int i;
        for(i=n;i>=0;i--)
        {
            Log.d("TASK", "i:" + i);
            publishProgress(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "OK";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        tv.setText(String.valueOf(values[0]));
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("TASK", s);
    }
}