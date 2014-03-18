

package org.example.sensortest;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorTest extends Activity implements SensorEventListener {
   

   
   
   private SensorManager mgr;
   
   private TextView output;
   
   private List<Sensor> sensorList;
   

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      
      // ...
      mgr = (SensorManager) getSystemService(SENSOR_SERVICE);
      
      output = (TextView) findViewById(R.id.output);
   }
   

   
   @Override
   protected void onResume() {
      super.onResume();
      
      // Start updates for one or more sensors
      sensorList = mgr.getSensorList(Sensor.TYPE_ALL);
      for (Sensor sensor : sensorList) {
         mgr.registerListener(this, sensor,
                 SensorManager.SENSOR_DELAY_NORMAL);
      }
      
   }

   @Override
   protected void onPause() {
      super.onPause();
      
      // Stop updates to save power while app paused
      mgr.unregisterListener(this);
      
   }
   

   public void onAccuracyChanged(Sensor sensor, int accuracy) {
      // TODO Auto-generated method stub
   }

   
   public void onSensorChanged(SensorEvent event) {
      
      StringBuilder builder = new StringBuilder();
      builder.append("--- SENSOR ---");
      builder.append("\nName: ");
      Sensor sensor = event.sensor;
      builder.append(sensor.getName());
      builder.append("\nType: ");
      builder.append(sensor.getType());
      builder.append("\nVendor: ");
      builder.append(sensor.getVendor());
      builder.append("\nVersion: ");
      builder.append(sensor.getVersion());
      builder.append("\nMaximum Range: ");
      builder.append(sensor.getMaximumRange());
      builder.append("\nPower: ");
      builder.append(sensor.getPower());
      builder.append("\nResolution: ");
      builder.append(sensor.getResolution());

      builder.append("\n\n--- EVENT ---");
      builder.append("\nAccuracy: ");
      builder.append(event.accuracy);
      builder.append("\nTimestamp: ");
      builder.append(event.timestamp);
      builder.append("\nValues:\n");
      
      for (int i = 0; i < event.values.length; i++) {
         // ...
         
         builder.append("   [");
         builder.append(i);
         builder.append("] = ");
         builder.append(event.values[i]);
         builder.append("\n");
         
      }
      
      output.setText(builder);
      
   }
   

   
}
