package com.pros.ossproj.service;

/**
 * Created by ruwn on 2017-03-09.
 */

public class WorriorLocation {

    private int _id = 0;

    private String time;
    private String accuracy;
    private String latitude;
    private String longitude;

    public int get_id(){
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
