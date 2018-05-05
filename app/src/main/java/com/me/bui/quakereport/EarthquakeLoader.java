package com.me.bui.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.me.bui.quakereport.data.Earthquake;

import java.util.List;

/**
 * Created by mao.bui on 5/5/2018.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static final String TAG = EarthquakeLoader.class.getSimpleName();

    private String mUrl;
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(TAG, "loadInBackground ... called.");
        if (mUrl == null)
            return null;
        return Utils.fetchEarthquakeData(mUrl);
    }

    @Override
    protected void onStartLoading() {
        Log.i(TAG, "onStartLoading ... called.");
        forceLoad();
    }
}
