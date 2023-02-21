package com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.api;

import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity.PostalCodes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//161.97.141.113:50520/api/from/geonames/postalCodeSearchJSON/codes?code=34430

public interface IPostalCodeSearch {
    @GET("/api/from/geonames/postalCodeSearchJSON/codes")
    Call<PostalCodes> findPostalCode(@Query("code")String postalCode);
}
