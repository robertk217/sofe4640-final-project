package com.example.a100536625.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class EditAppointmentActivity extends AppCompatActivity {
    private long id;

    private EditText txtName;
    private EditText txtDate;
    private EditText txtTime;
    private EditText txtNotes;

    private AppointmentHelper appointmentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);

        Intent callingIntent = getIntent();
        id = callingIntent.getLongExtra("id", -1);

        appointmentHelper = new AppointmentHelper(this);
        Appointment appointment = appointmentHelper.getAppointment(id);

        txtName = (EditText) findViewById(R.id.txtName);
        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);
        txtNotes = (EditText) findViewById(R.id.txtNotes);

        if (appointment != null) {
            txtName.setText(appointment.getName());
            txtDate.setText(appointment.getDate());
            txtTime.setText(appointment.getTime());
            txtNotes.setText(appointment.getNotes());
        }
    }

    private void storeAppointmentData() {
        String name = txtName.getText().toString();
        String date = txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String notes = txtNotes.getText().toString();

        if (id == -1) {
            // add a new appointment
            appointmentHelper.createAppointment(name, date, time, notes);
        } else {
            // update the contact
            Appointment appointment = new Appointment(name, date, time, notes);
            appointment.setId(id);

            appointmentHelper.updateAppointment(appointment);
        }
    }

    private void deleteAppointment() {
        appointmentHelper.deleteAppointment(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appointment_details, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuBack) {
            storeAppointmentData();
            finish();
            return true;
        } else if (id == R.id.mnuDelete) {
            deleteAppointment();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
