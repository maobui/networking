package com.me.bui.quakereport.data;

/**
 * Created by mao.bui on 4/27/2018.
 */
public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private int mFelt;
    private double mCdi;
    private int mTsunami;
    private String mTitle;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, int felt, double cdi, int tsunami, String title) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mFelt = felt;
        mCdi = cdi;
        mTsunami = tsunami;
        mTitle = title;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    public int getFelt() {
        return mFelt;
    }

    public double getCdi() {
        return mCdi;
    }

    public int getTsunami() {
        return mTsunami;
    }

    public String getTitle() {
        return mTitle;
    }
}
