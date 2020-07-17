package com.anadi.weatherinfo.repository.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Wind implements Serializable {

    // Wind speed.
    // Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
    @SerializedName("speed")
    @Expose
    public float speed;

    // Wind direction, degrees (meteorological)
    @SerializedName("deg")
    @Expose
    public int deg;

}