package org.csystem.android.app.geonames.postalcodesearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.borasahin.android.library.geonames.postalcode.data.service.PostalCodeAppService
import com.borasahin.android.library.geonames.postalcode.data.service.mapper.geonames.IPostalCodeMapper
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.api.IPostalCodeSearch
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity.PostalCodes
import com.karandev.util.retrofit.RetrofitUtil
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var postalCodeSearch: IPostalCodeSearch

    @Inject
    lateinit var postalCodeAppService: PostalCodeAppService

    @Inject
    lateinit var postalCodeMapper: IPostalCodeMapper

    private fun responseCallback(response: Response<PostalCodes>)
    {
        val postalCodes = response.body()

        if (postalCodes != null) {
            val places = postalCodes.codes.map { it.placeName }.reduce { r, p -> "$r $p" }.toString()

            val dtos = postalCodes.codes.map { it.code = 34387.toString(); postalCodeMapper.toPostalCodeSaveDTO(it) }

            postalCodeAppService.savePostalCode(dtos, {Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()})
                        {Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()}

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
        val call = postalCodeSearch.findPostalCode("34387")

        RetrofitUtil.enqueue(call, {_, r -> responseCallback(r)}) {c, ex -> failCallback(c, ex)}
    }
}