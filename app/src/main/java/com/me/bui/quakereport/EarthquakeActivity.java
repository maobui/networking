/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.me.bui.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.me.bui.quakereport.data.Earthquake;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-01-01&endtime=2018-02-01";

    private ArrayList<Earthquake> mEarthquakes = new ArrayList<>();
    private EarthquakeAdapter mAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        mProgressBar = findViewById(R.id.loading);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        mAdapter = new EarthquakeAdapter(this, mEarthquakes);
        earthquakeListView.setAdapter(mAdapter);

        EarthAnsynTask task = new EarthAnsynTask();
        task.execute(USGS_REQUEST_URL);
    }

    private void updateUi (ArrayList<Earthquake> earthquakes) {
        mProgressBar.setVisibility(View.INVISIBLE);
        mEarthquakes.addAll(earthquakes);
        mAdapter.notifyDataSetChanged();
    }

    private class EarthAnsynTask extends AsyncTask<String , Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            return Utils.fetchEarthquakeData(urls[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquake) {
            if (earthquake == null){
                Log.e(TAG, "onPostExecute -------------------------------- earthquake is NULL");
                return;
            }
            updateUi(earthquake);
        }
    }
}
