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

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.me.bui.quakereport.data.Earthquake;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>>{

    public static final String TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&limit=10";

    private ArrayList<Earthquake> mEarthquakes = new ArrayList<>();
    private EarthquakeAdapter mAdapter;
    private ProgressBar mProgressBar;
    private TextView mTxtEmptyState;

    private static final int EARTHQUAKE_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        mProgressBar = findViewById(R.id.loading);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        mAdapter = new EarthquakeAdapter(this, mEarthquakes);
        earthquakeListView.setAdapter(mAdapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(EarthquakeActivity.this, EarthDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("mag",mAdapter.getItem(position).getMagnitude());
                bundle.putInt("felt",mAdapter.getItem(position).getFelt());
                bundle.putDouble("cdi",mAdapter.getItem(position).getCdi());
                bundle.putInt("tsunami",mAdapter.getItem(position).getTsunami());
                bundle.putString("title",mAdapter.getItem(position).getTitle());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mTxtEmptyState = findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(mTxtEmptyState);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo  = connMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
        } else {
            mTxtEmptyState.setText(R.string.no_internet_connection);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void updateUi (List<Earthquake> earthquakes) {
        mAdapter.clear();
        mTxtEmptyState.setText(R.string.no_earthquakes);
        mProgressBar.setVisibility(View.GONE);
        mAdapter.setEarthquakes(earthquakes);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.i(TAG, "onCreateLoader ... called.");
        return new EarthquakeLoader(this,USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        Log.i(TAG, "onLoadFinished ... called.");
        updateUi(earthquakes);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.i(TAG, "onLoaderReset ... called.");
        mAdapter.clear();
    }
}
