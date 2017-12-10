package com.example.a100536625.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import com.example.a100536625.finalproject.Appointment;
import com.example.a100536625.finalproject.AppointmentHelper;


import static android.R.attr.bitmap;
import static android.graphics.BitmapFactory.decodeResource;


public class MyAppointments extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    public static int APPOINTMENT_DETAILS_REQUEST = 1;

    private AppointmentArrayAdapter appointmentArrayAdapter;
    private ListView appointmentList;
    private AppointmentHelper appointmentHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        appointmentList = (ListView)findViewById(R.id.lstAppointments);

        appointmentHelper = new AppointmentHelper(this);
        updateAppointmentList();

        Log.i("SQLite", "calling setOnItemClickListener()");
        appointmentList.setOnItemClickListener(this);


    }



    private void updateAppointmentList() {
        List<Appointment> appointments = appointmentHelper.getAllAppointments();
        appointmentArrayAdapter = new AppointmentArrayAdapter(this, appointments);
        appointmentList.setAdapter(appointmentArrayAdapter);
    }

    private void showEditAppointment(long id) {
        Intent editAppointmentIntent = new Intent(this, EditAppointmentActivity.class);
        editAppointmentIntent.putExtra("id", id);
        startActivityForResult(editAppointmentIntent, APPOINTMENT_DETAILS_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int responseCode,
                                 Intent result) {
        if (requestCode == APPOINTMENT_DETAILS_REQUEST) {
            updateAppointmentList();
        }
    }

    @Override
    public void onItemClick(AdapterView aView, View source, int position, long id) {
        Appointment appointment = (Appointment)appointmentArrayAdapter.getItem(position);
        Log.i("SQLite", "selected appointment: " + appointment);

        // show the edit appointment activity
        showEditAppointment(appointment.getId());
    }

    public void add(View source) {
        Log.i("SQLite", "adding new appointment");

        // show the edit appointment activity
        showEditAppointment(-1);
    }

    /* kept for reference, but this is no longer called */
    /*private void testModel() {
        ContactHelper contactHelper = new ContactHelper(this);

        contactHelper.deleteAllContacts();

        Contact bsmith = contactHelper.createContact("Barb",
                "Smith",
                "bsmith@uoit.ca",
                "905-721-8668");

        Contact lramirez = contactHelper.createContact("Luis",
                "Ramirez",
                "lramirez@utoronto.ca",
                "416-123-4567");

        Contact deleteMe = contactHelper.createContact("Tobe",
                "Deleted",
                "deleteme@soon.com",
                "213-888-9999");

        Log.i("SQLite", "getAllContacts():");
        List<Contact> allContacts = contactHelper.getAllContacts();
        for (int i = 0; i < allContacts.size(); i++) {
            Contact current = allContacts.get(i);
            Log.i("SQLite", current.getFirstName() + " " + current.getLastName() +
                    " - " + current.getPhone());
        }

        Contact lramirez2 = contactHelper.getContact(lramirez.getId());
        Log.i("SQLite", "getContact():");
        Log.i("SQLite", lramirez2.getFirstName() + " " + lramirez2.getLastName());

        lramirez2.setPhone("111-222-3333");
        boolean updateSuccess = contactHelper.updateContact(lramirez2);
        Log.i("SQLite", "updateSuccess == " + updateSuccess);

        boolean deleteSuccess = contactHelper.deleteContact(deleteMe.getId());
        Log.i("SQLite", "deleteSuccess == " + deleteSuccess);

        Log.i("SQLite", "getAllContacts():");
        allContacts = contactHelper.getAllContacts();
        for (int i = 0; i < allContacts.size(); i++) {
            Contact current = allContacts.get(i);
            Log.i("SQLite", current.getFirstName() + " " + current.getLastName() +
                    " - " + current.getPhone());
        }
    } */
}