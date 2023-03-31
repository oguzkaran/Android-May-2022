package org.csystem.android.app.geonames.postalcodesearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.borasahin.android.library.geonames.postalcode.data.service.PostalCodeAppService
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
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

    private fun saveResultCallback(result: Boolean)
    {
        Toast.makeText(this, if (result) "Success" else "Fail", Toast.LENGTH_LONG).show()
    }

    private fun saveFailCallback(ex: Throwable)
    {
        Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
    }

    private fun findPostalCodesByCodeResultCallback(postalCodes: MutableIterable<PostalCodeDTO>, found: Boolean)
    {
        if (postalCodes.count() > 0) {
            AlertDialog.Builder(this)
                .setTitle(R.string.alert_dialog_title_text)
                .setMessage(R.string.alert_dialog_message_text)
                .setPositiveButton(R.string.alert_dialog_positive_button_text)
                {_, _ -> postalCodeAppService.savePostalCodeDTO(postalCodes.toList(), {saveResultCallback(it)}) {saveFailCallback(it)} }
                .setNegativeButton(R.string.alert_dialog_negative_button_text) {_, _ -> Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()}
                .setNeutralButton(R.string.alert_dialog_neutral_button_text) {_, _ -> Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()}
                .create()
                .show()

            val places = postalCodes.map { it.placeName }.toString()

            Toast.makeText(this, places, Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show()
    }

    private fun findPostalCodesByCodeFailCallback(ex: DataServiceException)
    {
        Toast.makeText(this@MainActivity, "${ex.cause?.javaClass?.name}${ex.message}", Toast.LENGTH_LONG).show()
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
                {it, found -> findPostalCodesByCodeResultCallback(it, found)}) {findPostalCodesByCodeFailCallback(it)}
        }
        catch (ex: NumberFormatException) {
            Toast.makeText(this, "Invalid code value!...", Toast.LENGTH_LONG).show()
        }
    }
}