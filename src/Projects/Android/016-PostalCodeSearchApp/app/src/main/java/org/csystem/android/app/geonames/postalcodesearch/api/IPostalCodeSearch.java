package org.csystem.android.app.geonames.postalcodesearch.api;

import org.csystem.android.app.geonames.postalcodesearch.data.entity.PostalCodes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//http://api.geonames.org/postalCodeSearchJSON?postalcode=34387&maxRows=10&username=csystem&country=tr
public interface IPostalCodeSearch {
    @GET("/postalCodeSearchJSON")
    Call<PostalCodes> findPostalCode(@Query("username") String username,
                                     @Query("country")String country,
                                     @Query("postalcode") int postalCode,
                                     @Query("maxRows") int maxRow);
}
