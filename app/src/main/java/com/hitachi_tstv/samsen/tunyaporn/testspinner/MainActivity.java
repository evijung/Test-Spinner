package com.hitachi_tstv.samsen.tunyaporn.testspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    //Explicit
    private String array_spinner[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Data
        array_spinner = new String[5];
        array_spinner[0] = "Rose";
        array_spinner[1] = "Orchid";
        array_spinner[2] = "Lavender";
        array_spinner[3] = "Cosmos";
        array_spinner[4] = "Cherry Blossom";

        //Bind Widget
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, array_spinner);
        spinner.setAdapter(arrayAdapter);
    }
}
