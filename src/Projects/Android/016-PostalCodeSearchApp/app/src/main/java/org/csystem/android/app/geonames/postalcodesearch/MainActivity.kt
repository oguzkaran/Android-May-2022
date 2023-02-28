package org.csystem.android.app.geonames.postalcodesearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.borasahin.android.library.geonames.postalcode.data.service.PostalCodeAppService
import com.borasahin.android.library.geonames.postalcode.data.service.mapper.geonames.IPostalCodeMapper
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.api.IPostalCodeSearch
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity.PostalCodes
import com.karandev.util.retrofit.RetrofitUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.geonames.postalcodesearch.databinding.ActivityMainBinding
import org.csystem.android.app.geonames.postalcodesearch.viewmodel.MainActivityViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    @Inject
    lateinit var postalCodeSearch: IPostalCodeSearch

    @Inject
    lateinit var postalCodeAppService: PostalCodeAppService

    @Inject
    lateinit var postalCodeMapper: IPostalCodeMapper

    private fun responseCallback(response: Response<PostalCodes>)
    {
        val code = mBinding.viewModel?.code

        try {

            val postalCodes = response.body()

            if (postalCodes != null) {
                val places =
                    postalCodes.codes.map { it.placeName }.reduce { r, p -> "$r $p" }.toString()

                val dtos = postalCodes.codes.map {
                    it.code = code; postalCodeMapper.toPostalCodeSaveDTO(it)
                }

                /*
                val postalCodes = postalCodeAppService.findPostalCodesByCode(code!!.toInt(), {
                    postalCodeAppService.savePostalCode(dtos,
                        { Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show() })
                    { Toast.makeText(this, it.message, Toast.LENGTH_LONG).show() }
                })
                */
                
                Toast.makeText(this, places, Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(this, "Problem Occurs", Toast.LENGTH_LONG).show()
        }
        catch (ex: UnsupportedOperationException) {
            Toast.makeText(this, "No place for code:$code", Toast.LENGTH_LONG).show()
        }
        catch (ex: java.lang.NumberFormatException) {
            Toast.makeText(this, "Invbalid code value", Toast.LENGTH_LONG).show()
        }
    }

    private fun failCallback(call: Call<PostalCodes>, ex: Throwable)
    {
        Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_LONG).show()
        call.cancel()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun findAllPlacesButtonClicked()
    {
        val call = postalCodeSearch.findPostalCode(mBinding.viewModel!!.code)

        RetrofitUtil.enqueue(call, { _, r -> responseCallback(r) }) { c, ex ->
            failCallback(
                c,
                ex
            )
        }
    }
}