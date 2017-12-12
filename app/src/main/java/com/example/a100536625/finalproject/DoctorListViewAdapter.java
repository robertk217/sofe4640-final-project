package com.example.a100536625.finalproject;

/**
 * Created by shahrukhzarir on 2017-12-11.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class DoctorListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Doctor> doctorlist = null;
    private ArrayList<Doctor> arraylist;

    public DoctorListViewAdapter(Context context, List<Doctor> doctorlist) {
        mContext = context;
        this.doctorlist = doctorlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Doctor>();
        this.arraylist.addAll(doctorlist);
    }

    public class ViewHolder {
        TextView firstName;
        TextView lastName;
        TextView location;
        TextView city;
    }

    @Override
    public int getCount() {
        return doctorlist.size();
    }

    @Override
    public Doctor getItem(int position) {
        return doctorlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.firstName = (TextView) view.findViewById(R.id.first_name);
            holder.lastName = (TextView) view.findViewById(R.id.last_name);
            holder.location = (TextView) view.findViewById(R.id.location);
            holder.city = (TextView) view.findViewById(R.id.city);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.firstName.setText(doctorlist.get(position).getFirstName());
        holder.lastName.setText(doctorlist.get(position).getLastName());
        holder.location.setText(doctorlist.get(position).getLocation());
        holder.city.setText(doctorlist.get(position).getCity());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleDoctorView.class);
                // Pass all data first name
                intent.putExtra("firstName",(doctorlist.get(position).getFirstName()));
                // Pass all data last name
                intent.putExtra("lastName",(doctorlist.get(position).getLastName()));
                // Pass all data location
                intent.putExtra("location",(doctorlist.get(position).getLocation()));
                // Pass all data city
                intent.putExtra("city",(doctorlist.get(position).getCity()));
                // Pass all data flag
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        doctorlist.clear();
        if (charText.length() == 0) {
            doctorlist.addAll(arraylist);
        }
        else
        {
            for (Doctor wp : arraylist)
            {
                if (wp.getLastName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    doctorlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}