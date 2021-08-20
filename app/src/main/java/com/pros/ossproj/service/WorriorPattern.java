package com.pros.ossproj.service;

public class WorriorPattern {

    private int _id = 0;

    private String act_date;
    private String act_do;
    private String act_location;

    public int get_id(){
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAct_date() {
        return act_date;
    }

    public void setAct_date(String act_date) {
        this.act_date = act_date;
    }

    public String getAct_do() {
        return act_do;
    }

    public void setAct_do(String act_do) {
        this.act_do = act_do;
    }

    public String getAct_location() {
        return act_location;
    }

    public void setAct_location(String act_location) {
        this.act_location = act_location;
    }
}
