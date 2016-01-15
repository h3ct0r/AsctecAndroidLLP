package br.ufmg.dcc.verlab.android.asctecAndroidLLP;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    AsctecDriver driver = null;
    Boolean isConnected = false;
    TextView textMain = null;
    Activity act = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        driver = new AsctecDriver();
        act = this;

        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        textMain = (TextView) findViewById(R.id.text_main);

        Button btnConnect = (Button) findViewById(R.id.btn_connect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean success = false;
                if (!isConnected) {
                    try {
                        driver.connect(getSerialParameters(act));
                        textMain.setText(">> Connected");
                        success = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        textMain.setText(">> " + e.getMessage());
                    }
                } else {
                    try {
                        driver.disconnect();
                        textMain.setText(">> Disconnected");
                        success = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        textMain.setText(">> " + e.getMessage());
                    }
                }
                if(success) isConnected = !isConnected;
            }
        });

        // Button for GPS
        Button btnGps = (Button) findViewById(R.id.btn_gps);
        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double[] senseGPS = driver.senseGPS();

                if (senseGPS == null) {
                    textMain.setText(">> error");
                    return;
                }

                textMain.setText(">> lat: " + senseGPS[0] + ", long:" + senseGPS[1]);
            }
        });

        // Button for IMU
        Button btnImu = (Button) findViewById(R.id.btn_imu);
        btnImu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double[] sensed = driver.senseIMU();

                if (sensed == null) {
                    textMain.setText(">> error");
                    return;
                }

                textMain.setText(">> g: " + sensed[0] + ", " + sensed[1] + ", " + sensed[2]);
            }
        });
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

    private HashMap<String, Object> getSerialParameters(Activity act) {
        HashMap<String, Object> params = new HashMap<String, Object>();

        Log.i(this.getClass().getName(), "Activity -> " + act);

        params.put("Activity", act);
        params.put("baudrate", 57600);
        params.put("dataBits", 8);
        params.put("stopBits", 1);
        params.put("parity", 0);

        return params;
    }
}
