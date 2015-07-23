package com.example.noa.timer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.os.SystemClock;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.ArrayList;



public class MainActivity extends Activity implements View.OnClickListener {

    ArrayList<Pair> list = new ArrayList<Pair>();

    long startTime = 0;

    long stopTime = 0;

    private Chronometer chronometer;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        ((Button) findViewById(R.id.start_button)).setOnClickListener(this);

        ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);

        //Thread for real time clock
        Thread myThread = null;

        Runnable myRunnableThread = new CountDownRunner();
        myThread= new Thread(myRunnableThread);
        myThread.start();
    }

    @Override

    public void onClick(View v) {

         switch(v.getId()) {

            case R.id.start_button:
                startTime = System.currentTimeMillis();
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();


                String stime = DateFormat.getTimeInstance().format(startTime);
                TextView sttime = (TextView) findViewById(R.id.strt_time);
                sttime.setText("Start: " + stime);

                break;

            case R.id.stop_button:

                stopTime = System.currentTimeMillis();
                chronometer.stop();


                TextView stptime = (TextView) findViewById(R.id.stop_time);
                String etime = DateFormat.getTimeInstance().format(stopTime);
                stptime.setText("Stop: " + etime);

                // Add a new value pair to list: start time and endtime (as longs)
                list.add(new Pair(startTime, stopTime));

                break;
        }
    }

        //Open next activity and pass the Arraylist as a Parcelable Extra
        public void sendList(View view) {

            Intent intent = new Intent(this, ListActivity.class);
            intent.putParcelableArrayListExtra("LIST", list);
            startActivity(intent);
        }

    /**********************************************/
    //             Real Time Clock                 /
    /**********************************************/

    public class CountDownRunner implements Runnable{
        // @Override
        public void run() {

            while(!Thread.currentThread().isInterrupted()){
                try {
                    doWork();
                    Thread.sleep(1000); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
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

    /**********************************************/
    //             Real Time Clock                 /
    /**********************************************/

    }

