package com.sensorlist;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Gyroscope extends Activity implements SensorEventListener {

	Sensor gyroscope;
	SensorManager sm;
	TextView acceleration;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gyroscope);
		sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		gyroscope =sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		sm.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
		
		acceleration = (TextView)findViewById(R.id.textView1); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gyroscope, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		acceleration.setText("X: "+arg0.values[0]
				+"\nY: "+arg0.values[1]
				+"\nZ: "+arg0.values[2]);
	}
}
