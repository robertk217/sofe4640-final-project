package com.example.a100536625.finalproject;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.CalendarContract;
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

    private String firstName;
    private String lastName;

    private AppointmentHelper appointmentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);

        Intent callingIntent = getIntent();
        id = callingIntent.getLongExtra("id", -1);
        // Get the results of first name
        firstName = callingIntent.getStringExtra("firstName");
        // Get the results of last name
        lastName = callingIntent.getStringExtra("lastName");


        appointmentHelper = new AppointmentHelper(this);
        Appointment appointment = appointmentHelper.getAppointment(id);

        if (firstName != null && lastName != null){
            txtName.setText("Dr." + firstName + " " + lastName);
        }

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

            String[] split = date.split("/");
            int year = Integer.parseInt(split[0]);
            int month = Integer.parseInt(split[1]) - 1;
            int day = Integer.parseInt(split[2]);
            split = time.split(":");
            int hours = Integer.parseInt(split[0]);
            int mins = Integer.parseInt(split[1]);
            updateCalendar(year, month, day, hours, mins, name, notes);

        } else {
            // update the contact
            Appointment appointment = new Appointment(name, date, time, notes);
            appointment.setId(id);

            appointmentHelper.updateAppointment(appointment);
        }
    }
    public void updateCalendar(int year, int month, int day, int hours, int mins, String name, String notes) {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(year, month, day, hours, mins);
        Calendar endTime = Calendar.getInstance();
       // endTime.set(2012, 0, 19, 8, 30);
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                //.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, name)
                .putExtra(CalendarContract.Events.DESCRIPTION, notes)
                //.putExtra(CalendarContract.Events.EVENT_LOCATION, "The gym")
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
                .putExtra(Intent.EXTRA_EMAIL, "tom@example.com");
        startActivity(intent);
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