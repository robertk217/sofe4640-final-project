package com.example.a100536625.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SingleDoctorView extends AppCompatActivity {

    // Declare Variables
    TextView txtFirstName;
    TextView txtLastName;
    TextView txtCity;
    TextView txtLocation;

    String firstName;
    String lastName;
    String location;
    String city;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of first name
        firstName = i.getStringExtra("firstName");
        // Get the results of last name
        lastName = i.getStringExtra("lastName");
        // Get the results of location
        location = i.getStringExtra("location");
        // Get the results of population
        city = i.getStringExtra("city");

        // Locate the TextViews in singleitemview.xml
        txtFirstName = (TextView) findViewById(R.id.first_name);
        txtLastName = (TextView) findViewById(R.id.last_name);
        txtLocation = (TextView) findViewById(R.id.location);
        txtCity = (TextView) findViewById(R.id.city);

        // Load the results into the TextViews
        txtFirstName.setText(firstName);
        txtLastName.setText(lastName);
        txtLocation.setText(location);
        txtCity.setText(city);
    }

    public void onBookAppointment() {
        Intent intent = new Intent(this, EditAppointmentActivity.class);
        intent.putExtra("firstName", firstName);
        intent.putExtra("lastName", lastName);
        startActivity(intent);
    }

}
