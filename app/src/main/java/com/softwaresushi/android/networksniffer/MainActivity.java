package com.softwaresushi.android.networksniffer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.softwaresushi.android.myapplication.R;

import java.lang.ref.WeakReference;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = Constants.TAG + "main";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setupUI();
  }

  private void setupUI() {
    Button sniffButton = (Button) findViewById(R.id.sniffButton);

    sniffButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Log.d(TAG, "Sniff Button Clicked.");

        new NetworkSniffTask(getApplicationContext()).execute(null, null, null);
      }
    });
  }

  static class NetworkSniffTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = Constants.TAG + "nstask";

    private WeakReference<Context> mContextRef;

    public NetworkSniffTask(Context context) {
      mContextRef = new WeakReference<Context>(context);
    }

    @Override
    protected Void doInBackground(Void... voids) {
      Log.d(TAG, "Let's sniff the network");

      try {
        Context context = mContextRef.get();

        if (context != null) {

          ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
          WifiManager wm = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);

          WifiInfo connectionInfo = wm.getConnectionInfo();
          int ipAddress = connectionInfo.getIpAddress();
          String ipString = Formatter.formatIpAddress(ipAddress);


          Log.d(TAG, "activeNetwork: " + String.valueOf(activeNetwork));
          Log.d(TAG, "ipString: " + String.valueOf(ipString));

          String subnet = ipString.substring(0, ipString.lastIndexOf(".") + 1);
          Log.d(TAG, "subnet: " + subnet);

          for (int i = 0; i < 255; i++) {
            String testIp = subnet + String.valueOf(i);
            //Log.d(TAG, "Trying ip: " + testIp);
            InetAddress address = InetAddress.getByName(testIp);
            boolean reachable = address.isReachable(400);
            String hostName = address.getHostName();
            String cHostName = address.getCanonicalHostName();

            if (reachable)
              Log.i(TAG, "Host: " + String.valueOf(hostName) + "[" + String.valueOf(cHostName) + "](" + String.valueOf(testIp) + ") is reachable!");

            //Log.d(TAG, "Reachable? " + String.valueOf(reachable));
          }
        }
      } catch (Throwable t) {
        Log.e(TAG, "Well that's not good.", t);
      }

      return null;
    }
  }
}
