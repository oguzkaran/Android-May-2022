package org.csystem.android.app.geonames.postalcodesearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.borasahin.android.library.geonames.postalcode.data.service.PostalCodeAppService
import com.gokhandiyaroglu.android.app.geonames.postalcodesearch.api.GEONAMES_BASE_URL
import com.gokhandiyaroglu.android.app.geonames.postalcodesearch.data.mapper.PostalCodeMapper
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.api.IPostalCodeSearch
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity.PostalCodes
import com.karandev.util.retrofit.RetrofitUtil
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var postalCodeSearch: IPostalCodeSearch

    @Inject
    lateinit var postalCodeAppService: PostalCodeAppService

    @Inject
    lateinit var postalCodeMapper: PostalCodeMapper

    private fun responseCallback(response: Response<PostalCodes>)
    {
        val postalCodes = response.body()

        if (postalCodes != null) {
            val places = postalCodes.codes.map { it.placeName }.reduce { r, p -> "$r $p" }.toString()

            Toast.makeText(this, places, Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "Problem Occurs", Toast.LENGTH_LONG).show()
    }

    private fun failCallback(call: Call<PostalCodes>, ex: Throwable)
    {
        Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_LONG).show()
        call.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listPlacesButtonClicked()
    }

    fun listPlacesButtonClicked()
    {
        postalCodeSearch = RetrofitUtil.createRetrofitWithLogging(GEONAMES_BASE_URL).create(IPostalCodeSearch::class.java)
        val call = postalCodeSearch.findPostalCode("csystem", "tr", 67000, 10)

        RetrofitUtil.enqueue(call, {_, r -> responseCallback(r)}) {c, ex -> failCallback(c, ex)}
    }
}