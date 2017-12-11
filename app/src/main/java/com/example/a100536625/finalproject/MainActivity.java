package com.example.a100536625.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 100536625 on 12/10/2017.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
public void onSearch(View v) {
    //Intent intent = new Intent(this, SearchActivity.class);
    //startActivity(intent);
}

public void onAppt(View v) {
    Intent intent = new Intent(this, MyAppointments.class);
    startActivity(intent);
}
}