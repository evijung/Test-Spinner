package com.hitachi_tstv.samsen.tunyaporn.testspinner;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //Explicit

    private final String urlJson = "http://service.eternity.co.th/tires/system/centerservice/getVehicle.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Set Data
//        array_spinner = new String[6];
//        array_spinner[0] = "Rose";
//        array_spinner[1] = "Orchid";
//        array_spinner[2] = "Lavender";
//        array_spinner[3] = "Cosmos";
//        array_spinner[4] = "Cherry Blossom";
//        array_spinner[5] = "Gypsophilla";

        //Bind Widget
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        SyncVehicle syncVehicle = new SyncVehicle(this, urlJson, spinner);
        syncVehicle.execute();
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, array_spinner);
//        spinner.setAdapter(arrayAdapter);
    }

    private class SyncVehicle extends AsyncTask<Void, Void, String> {
        private Context context;
        private String myURL;
        private Spinner spinner;
        private String[] licenseStrings, idStrings;

        public SyncVehicle(Context context, String myURL, Spinner spinner) {
            this.context = context;
            this.myURL = myURL;
            this.spinner = spinner;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.url(myURL).build();
                Response response = okHttpClient.newCall(request).execute();

                return response.body().string();
            } catch (Exception e) {
                Log.d("SP1", "e doInBack ==> " + e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SP2", "JSON ==> " + s);

            try {
                JSONArray jsonArray = new JSONArray(s);

                licenseStrings = new String[jsonArray.length()];
                idStrings = new String[jsonArray.length()];

                for (int i = 0;i < jsonArray.length();i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    licenseStrings[i] = jsonObject.getString("veh_license");
                    idStrings[i] = jsonObject.getString("veh_id");
                }

                ArrayAdapter arrayAdapter = new ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, licenseStrings);
                spinner.setAdapter(arrayAdapter);

            } catch (JSONException e) {
                Log.d("SP2", "e doInBack ==> " + e.toString());
            }
        }
    }
}
