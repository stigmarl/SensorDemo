package stig.com.sensordemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity implements SensorEventListener{

    public SensorManager mSensorManager;
    public Sensor mSensor;
    private String data;
    private TextView sensorView, directionView;

    private double ax,ay,az;

    private static final String TAG = "SensorActivity";

    private enum directions {
        UP, DOWN, LEFT, RIGHT, NEUTRAL
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

        sensorView = (TextView) findViewById(R.id.sensorView);
        directionView = (TextView) findViewById(R.id.directionView);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        ax = event.values[0];
        ay = event.values[1];
        az = event.values[2];


        sensorView.setText(formatData(ax,ay,az));

        directionView.setText(getDirection());

        String l = getDirection();
        //Log.d(TAG, l);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    private String formatData(double x, double y, double z) {
        String xStr = "x: " + String.format("%.2f", x);
        String yStr = "y: " + String.format("%.2f", y);
        String zStr = "z: " + String.format("%.2f", z);

        return xStr + "\n" + yStr + "\n" + zStr + "\n";
    }

    public String getDirection(){
        String dirs[] = {"UP", "DOWN", "LEFT", "RIGHT", "NEUTRAL"};

        if (Math.abs(ay) < 15 ) {
            if (az < -5){
                return dirs[directions.UP.ordinal()];
            }
            if (az > 5)  {
                return dirs[directions.DOWN.ordinal()];
            }
        } else if (Math.abs(az) < 15 ) {
            if (ay < -5) {
                return dirs[directions.RIGHT.ordinal()];
            }
            if (az > 5) {
                return dirs[directions.LEFT.ordinal()];
            }
        }

        return dirs[directions.NEUTRAL.ordinal()];
    }
}

