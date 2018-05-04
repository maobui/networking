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

        txtMagnitude.setText(Utils.formatMagnitude(earthquake.getMagnitude()));
        String fullLocation [] =earthquake.getLocation().split("(?<=of )");
        if (fullLocation.length > 1) {
            txtLocationOffset.setText(fullLocation[0]);
            txtLocation.setText(fullLocation[1]);
        } else {
            txtLocationOffset.setText("");
            txtLocation.setText(fullLocation[0]);
        }

        Date date = new Date(earthquake.getTimeInMilliseconds());
        txtDate.setText(Utils.formatDate(date));
        txtTime.setText(Utils.formatTime(date));

        GradientDrawable magnitudeCircle = (GradientDrawable) txtMagnitude.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = Utils.getMagnitudeColor(getContext(), earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return convertView;
    }


}
