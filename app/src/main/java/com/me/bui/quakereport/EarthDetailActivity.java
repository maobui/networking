package com.me.bui.quakereport;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class EarthDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_detail);

        updateUi();
    }

    private void updateUi() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        double mag = bundle.getDouble("mag");
        int felt = bundle.getInt("felt");
        double cdi = bundle.getDouble("cdi");
        int tsunami = bundle.getInt("tsunami");
        String title = bundle.getString("title");

        TextView txtTitle = findViewById(R.id.title);
        TextView txtMag = findViewById(R.id.perceived_magnitude);
        TextView txtFelt = findViewById(R.id.number_of_people);

        txtTitle.setText(title);
        txtMag.setText(Utils.formatMagnitude(cdi));
        txtFelt.setText(getString(R.string.num_people_felt_it, Integer.toString(felt)));

        GradientDrawable magnitudeCircle = (GradientDrawable) txtMag.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = Utils.getMagnitudeColor(getApplicationContext(), cdi);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
    }
}
