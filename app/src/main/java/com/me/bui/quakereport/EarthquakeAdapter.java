package com.me.bui.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.me.bui.quakereport.data.Earthquake;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mao.bui on 4/27/2018.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, List<Earthquake> earthquakeList) {
        super(context, 0, earthquakeList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake earthquake = getItem(position);

        TextView txtMagnitude = convertView.findViewById(R.id.magnitude);
        TextView txtLocationOffset = convertView.findViewById(R.id.location_offset);
        TextView txtLocation = convertView.findViewById(R.id.location);
        TextView txtDate = convertView.findViewById(R.id.date);
        TextView txtTime = convertView.findViewById(R.id.time);

        txtMagnitude.setText(formatMagnitude(earthquake.getMagnitude()));
        String fullLocation [] =earthquake.getLocation().split("(?<=of )");
        if (fullLocation.length > 1) {
            txtLocationOffset.setText(fullLocation[0]);
            txtLocation.setText(fullLocation[1]);
        } else {
            txtLocationOffset.setText("");
            txtLocation.setText(fullLocation[0]);
        }

        Date date = new Date(earthquake.getTimeInMilliseconds());
        txtDate.setText(formatDate(date));
        txtTime.setText(formatTime(date));

        GradientDrawable magnitudeCircle = (GradientDrawable) txtMagnitude.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return convertView;
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM DD, yyyy");
        return formatter.format(date);
    }

    private String formatTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
        return formatter.format(date);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat format = new DecimalFormat("0.0");
        return  format.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
