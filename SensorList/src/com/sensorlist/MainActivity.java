package com.sensorlist;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	Button buttonAcelero;
	Button buttonGyro;
	Button buttonLight;
	static TextView rootdir;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SensorManager sManager = (SensorManager) this
				.getSystemService(this.SENSOR_SERVICE);
		List<Sensor> sList = sManager.getSensorList(Sensor.TYPE_ALL);

		List<String> sNames = new ArrayList();
		for (int i = 0; i < sList.size(); i++) {
			sNames.add(((Sensor) sList.get(i)).getName());
		}

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, sNames));
		
		getListView().setTextFilterEnabled(true);
		
		buttonAcelero = (Button) findViewById(R.id.button1);
		buttonAcelero.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent terserah = new Intent(MainActivity.this,Acelerometer.class);
				startActivity(terserah);
			}
		});
		
		buttonGyro = (Button) findViewById(R.id.button2);
		buttonGyro.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent gyro = new Intent(MainActivity.this,Gyroscope.class);
				startActivity(gyro);
			}
		});
		
		buttonLight = (Button) findViewById(R.id.button3);
		buttonLight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent light = new Intent(MainActivity.this,Light.class);
				startActivity(light);
			}
		});
		
		rootdir = (TextView) findViewById(R.id.textView1);
	}
	
	public static void write_csv(String filename,String data) {
        String root = Environment.getExternalStorageDirectory().toString();
        
        //String root = 
        rootdir.setText(root);
        File dir = new File(root+"/sensor");
        if(!dir.exists()){
            dir.mkdirs();
        }

        CharSequence date = DateFormat.format("yyyy-MM-dd HH:mm:ss", new java.util.Date());
        File file = new File(dir,date+" "+filename);

        try {
            FileOutputStream fs = new FileOutputStream(file);
            fs.write(data.getBytes());
            fs.close(); 
            //Toast.makeText(c, "File CSV tersimpan\n"+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
