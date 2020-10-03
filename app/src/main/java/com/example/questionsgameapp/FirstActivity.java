package com.example.questionsgameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity implements SensorEventListener {
    private Sensor sensor;
    private TextView tv;
    private SensorManager sensorManager;
    ArrayList<String> questions;
    ArrayList<String> t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        tv=findViewById(R.id.textView);
        questions=new ArrayList<>();

        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null){

        }else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();

        }
        sensorManager.registerListener(this,sensor,sensorManager.SENSOR_DELAY_NORMAL);




        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!=null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        }else {
            Toast.makeText(this,"sensor is not found",Toast.LENGTH_LONG).show();
        }
           Intent intent= getIntent();
           questions=intent.getStringArrayListExtra("text");

    }



    @Override
    public void onSensorChanged(final SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            if ( event.values[2]< -2){
                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
            }

        }else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY){
           if (event.values[0]==8){
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       while (true){
                           for (int i=0; i<questions.size();i++){
                               final int j=i;
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       tv.setText(questions.get(j));
                                   }
                               });
                               try {
                                   Thread.sleep(190);
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                               if (event.values[0]==0){
                                   break;
                               }
                           }
                           if (event.values[0]==0){
                               break;
                           }

                       }
                   }
               }).start();
           }



        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensor!=null){
            sensorManager.registerListener(this,sensor,sensorManager.SENSOR_DELAY_NORMAL);

        }else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();

        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


}
