package org.csystem.android.app.geonames.postalcodesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.karandev.util.retrofit.RetrofitUtil
import org.csystem.android.app.geonames.postalcodesearch.api.GEONAMES_BASE_URL
import org.csystem.android.app.geonames.postalcodesearch.api.IPostalCodeSearch
import org.csystem.android.app.geonames.postalcodesearch.data.entity.PostalCodes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listPlacesButtonClicked()
    }

    fun listPlacesButtonClicked()
    {
        val postalCodeSearch = RetrofitUtil.createRetrofitClientWithLogging(GEONAMES_BASE_URL).create(IPostalCodeSearch::class.java)
        val call = postalCodeSearch.findPostalCode("csystem", "tr", 67000, 10)

        call.enqueue(object: Callback<PostalCodes> {
            override fun onResponse(call: Call<PostalCodes>, response: Response<PostalCodes>)
            {
                val postalCodes = response.body()

                if (postalCodes != null) {
                    val places = postalCodes.codes.map {it.placeName}.reduce {r, p -> "$r $p" }.toString()

                    Toast.makeText(this@MainActivity, places, Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this@MainActivity, "Problem Occurs", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<PostalCodes>, ex: Throwable)
            {
                Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_LONG).show()
                call.cancel()
            }
        })
    }
}