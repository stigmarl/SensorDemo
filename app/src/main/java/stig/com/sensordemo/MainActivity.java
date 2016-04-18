package stig.com.sensordemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView textView = null;
    TextView dirView = null;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private double angleOffset = 10;
    private MovingAverage movAvg;

    private FragmentManager fm;
    FragmentTransaction ft;


    private enum directions {
        NEUTRAL, FORWARDS, BACKWARDS, LEFT, RIGHT;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getFragmentManager();
        ft = fm.beginTransaction();


        textView = (TextView) findViewById(R.id.accel);
        dirView = (TextView) findViewById(R.id.dir);





    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void onClick(View v){
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);


    }

}
