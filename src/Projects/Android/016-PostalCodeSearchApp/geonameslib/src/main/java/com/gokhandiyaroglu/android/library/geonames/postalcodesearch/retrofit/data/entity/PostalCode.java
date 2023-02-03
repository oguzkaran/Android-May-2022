package com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity;

import com.google.gson.annotations.SerializedName;

public class PostalCode {
    public String adminCode1;

    @SerializedName("lng")
    public double longitude;

    public String countryCode;

    public String postalCode;

    public String adminName1;

    public String placeName;

    @SerializedName("lat")
    public double latitude;

    @SerializedName("ISO3166-2")
    public String iSO31662Info;
}

