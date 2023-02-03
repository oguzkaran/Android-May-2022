package com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostalCodes {
    @SerializedName("postalCodes")
    public List<PostalCode> codes;
}
