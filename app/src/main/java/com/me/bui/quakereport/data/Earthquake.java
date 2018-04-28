package com.me.bui.quakereport.data;

/**
 * Created by mao.bui on 4/27/2018.
 */
public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private Long mTimeInMilliseconds;

    public Earthquake(String magnitude, String location, Long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public Long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
