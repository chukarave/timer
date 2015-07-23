package com.example.noa.timer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.os.SystemClock;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.ArrayList;



public class MainActivity extends Activity implements View.OnClickListener {

    public ArrayList<Pair<Long>> list = new ArrayList<Pair<Long>>();

    public long startTime = System.currentTimeMillis();

    public long stopTime = System.currentTimeMillis();

    private Chronometer chronometer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        ((Button) findViewById(R.id.start_button)).setOnClickListener(this);

        ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);


        Thread myThread = null;

        Runnable myRunnableThread = new CountDownRunner();
        myThread= new Thread(myRunnableThread);
        myThread.start();



    }


    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    TextView txtCurrentTime = (TextView) findViewById(R.id.current_time);

                    String curTime = DateFormat.getTimeInstance().format(System.currentTimeMillis());
                    txtCurrentTime.setText(curTime);
                } catch (Exception e) {
                }
            }
        });
    }


    @Override

    public void onClick(View v) {

      //  long startTime = System.currentTimeMillis();

     //   long stopTime = System.currentTimeMillis();





        switch(v.getId()) {

            case R.id.start_button:

                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();


                String stime = DateFormat.getTimeInstance().format(startTime);
                TextView sttime = (TextView) findViewById(R.id.strt_time);
                sttime.setText("Start: " + stime);


                break;

            case R.id.stop_button:

                chronometer.stop();


                TextView stptime = (TextView) findViewById(R.id.stop_time);

                String etime = DateFormat.getTimeInstance().format(stopTime);
                stptime.setText("Stop: " + etime);

                list.add(new Pair<Long>(startTime, stopTime));

                break;


        }



        }


    }







/*
public class MainActivity extends Activity implements OnClickListener {

    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); //choose layout

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        ((Button) findViewById(R.id.start_button)).setOnClickListener(this);

        ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {

            case R.id.start_button:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.stop_button:
                chronometer.stop();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

 */
