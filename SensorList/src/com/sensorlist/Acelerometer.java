package com.sensorlist;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Acelerometer extends Activity implements SensorEventListener {

	Button record;
	private boolean recording;
	private String data ="";
	Sensor accelerometer;
	SensorManager sm;
	TextView acceleration;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acelerometer);
		sm = (SensorManager)getSystemService(SENSOR_SERVICE);
		accelerometer =sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		
		recording = false;
		acceleration = (TextView)findViewById(R.id.textView1); 
		record = (Button) findViewById(R.id.button1);
		record.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(recording == false) 
				{
					recording = true;
					record.setText("Stop");
				}
				else 
				{
					record.setText("Start Record");
					recording = false;
					MainActivity.write_csv("Acelero.csv", data);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acelerometer, menu);
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
		if(recording == true)
		{
			data += arg0.values[0]+","+arg0.values[1]+","+arg0.values[2]+"\n";
		}
		acceleration.setText("X: "+arg0.values[0]
				+"\nY: "+arg0.values[1]
				+"\nZ: "+arg0.values[2]);
	}
}
