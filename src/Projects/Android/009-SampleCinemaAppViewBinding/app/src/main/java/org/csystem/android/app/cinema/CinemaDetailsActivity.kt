package org.csystem.android.app.cinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.csystem.android.app.cinema.data.CinemaType
import org.csystem.android.app.cinema.data.entity.Cinema
import org.csystem.android.app.cinema.databinding.ActivityCinemaDetailsBinding
import org.csystem.android.app.cinema.keys.CINEMA

class CinemaDetailsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityCinemaDetailsBinding
    private lateinit var mCinema: Cinema

    private fun getYearInfo() : Triple<Int, Boolean, String>
    {
        var result: Triple<Int, Boolean, String>

        try {
            result = Triple(mBinding.cinemaDetailsActivityEditTextYear.text.toString().toInt(), true, "")
        }
        catch (ignore: java.lang.NumberFormatException) {
            result = Triple(0, false, "Invalid year value")
        }

        return result
    }
    private fun updateCinemaObject()
    {
        mCinema.name = mBinding.cinemaDetailsActivityEditTextName .text.toString().trim()
        val yearInfo = getYearInfo()
        mCinema.type = mBinding.cinemaDetailsActivitySpinnerType.selectedItemPosition
        mCinema.director = mBinding.cinemaDetailsActivityEditTextDirector.text.trim().toString()
        mCinema.company = mBinding.cinemaDetailsActivityEditTextCompany.text.trim().toString()

        if (!yearInfo.second) {
            Toast.makeText(this, yearInfo.third, Toast.LENGTH_LONG).show()
            return
        }

        mCinema.year = yearInfo.first
    }

    private fun closeActivity()
    {
        setResult(RESULT_OK)
        finish()
    }
    private fun updateButtonAlertPositiveButtonCallback()
    {
        updateCinemaObject()
        CinemaRepository.update(mCinema)
        closeActivity()
    }

    private fun updateButtonClickCallback()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.message_title_cinema_details)
            .setMessage(R.string.message_text_cinema_details)
            .setPositiveButton(R.string.message_text_cinema_details_yes_button) {_, _ -> updateButtonAlertPositiveButtonCallback()}
            .setNegativeButton(R.string.message_text_cinema_details_no_button) {_, _-> ;}
            .create().show();
    }

    private fun initUpdateButton()
    {
        mBinding.cinemaDetailsActivityButtonUpdate.setOnClickListener{updateButtonClickCallback()}
    }

    private fun initData()
    {
        mBinding.cinemaDetailsActivityTextViewId.text = mCinema.id.toString()
        mBinding.cinemaDetailsActivityEditTextName.setText(mCinema.name)
        mBinding.cinemaDetailsActivityEditTextYear.setText(mCinema.year.toString())
        mBinding.cinemaDetailsActivityEditTextDirector.setText(mCinema.director)
        mBinding.cinemaDetailsActivityEditTextCompany.setText(mCinema.company)
    }

    private fun initTypeSpinner()
    {
        val values = CinemaType.values()
        val types = values.map{it.resourceName}.map{resources.getString(it)}.toTypedArray()

        mBinding.cinemaDetailsActivitySpinnerType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)

        mBinding.cinemaDetailsActivitySpinnerType.setSelection(mCinema.type)
    }

    private fun initViews()
    {
        initData()
        initTypeSpinner()
        initUpdateButton()
    }

    private fun initCinema()
    {
        mCinema = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(CINEMA) as Cinema
        else
            intent.getSerializableExtra(CINEMA, Cinema::class.java)!!
    }

    private fun initBinding()
    {
        mBinding = ActivityCinemaDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initCinema()
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}