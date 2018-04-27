package com.me.bui.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.me.bui.quakereport.data.Earthquake;

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
        TextView txtLocation = convertView.findViewById(R.id.location);
        TextView txtDate = convertView.findViewById(R.id.date);

        txtMagnitude.setText(earthquake.getMagnitude());
        txtLocation.setText(earthquake.getLocation());
        txtDate.setText(earthquake.getDate());

        return convertView;
    }
}
