package org.csystem.android.app.geonames.postalcodesearch.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostalCodes {
    @SerializedName("postalCodes")
    public List<PostalCode> codes;
}
