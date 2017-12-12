package com.example.a100536625.finalproject;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

public class SearchDoctor extends Activity {

    // Declare Variables
    ListView list;
    DoctorListViewAdapter adapter;
    EditText editsearch;
    String[] firstName;
    String[] lastName;
    String[] location;
    String[] city;
    ArrayList<Doctor> arraylist = new ArrayList<Doctor>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        // Generate sample data
        firstName = new String[] { "Shahrukh", "Robert", "Cherlyne", "Jonas", "Ebrahim", "Michael",
                "LeBron", "Kevin", "Stephen", "Demar" };

        lastName = new String[] { "Zarir", "Kocovski", "Santirarajan",
                "Albaira", "Merchant", "Jordan", "James", "Durant",
                "Curry", "DeRozan" };

        location = new String[] { "1 Charles St.", "2 Petunia St.",
                "3 Sarasota St.", "57 Cavalier St.", "58 Warrior Ave.", "35 Raptor Cres.",
                "17 Wizard Lane", "94 York Rd.", "86 Done Ave.", "90 Mobile St." };

        city = new String[] { "Toronto", "London",
                "Markham", "Oshawa", "Ajax", "Whitby",
                "Scarborough", "Mississauga", "Brampton", "Milton" };

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (int i = 0; i < firstName.length; i++)
        {
            Doctor wp = new Doctor(firstName[i], lastName[i],
                    location[i], city[i]);
            // Binds all strings into an array
            arraylist.add(wp);
        }

        // Pass results to ListViewAdapter Class
        adapter = new DoctorListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }

    // Not using options menu in this tutorial
   // @Override
    //public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.activity_search, menu);
      //  return true;
  //  }
}