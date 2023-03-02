package org.csystem.android.app.geonames.postalcodesearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.borasahin.android.library.geonames.postalcode.data.service.PostalCodeAppService
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
import com.borasahin.android.library.geonames.postalcode.data.service.mapper.geonames.IPostalCodeMapper
import com.karandev.util.data.service.DataServiceException
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.geonames.postalcodesearch.databinding.ActivityMainBinding
import org.csystem.android.app.geonames.postalcodesearch.viewmodel.MainActivityViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    @Inject
    lateinit var postalCodeAppService: PostalCodeAppService

    @Inject
    lateinit var postalCodeMapper: IPostalCodeMapper

    private fun findPostalCodesByCodeResultCallback(postalCodes: MutableIterable<PostalCodeDTO>)
    {
        val places = postalCodes.map { it.placeName }.toString()

        Toast.makeText(this, places, Toast.LENGTH_LONG).show()
    }

    private fun findPostalCodesByCodeFailCallback(ex: DataServiceException)
    {
        Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_LONG).show()
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
        try {
            postalCodeAppService.findPostalCodesByCode(mBinding.viewModel!!.code.toInt(),
                {findPostalCodesByCodeResultCallback(it)}) {findPostalCodesByCodeFailCallback(it)}
        }
        catch (ex: NumberFormatException) {
            Toast.makeText(this, "Invalid code value!...", Toast.LENGTH_LONG).show()
        }
    }
}