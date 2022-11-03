package org.csystem.android.app.cinema

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.cinema.data.CinemaType
import org.csystem.android.app.cinema.data.entity.Cinema
import org.csystem.android.app.cinema.databinding.ActivityCinemaDetailsBinding
import org.csystem.android.app.cinema.keys.CINEMA
import org.csystem.android.app.cinema.viewmodel.CinemaDetailsActivityViewModel

class CinemaDetailsActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityCinemaDetailsBinding

    private fun getMessage(cinema: CinemaDetailsActivityViewModel) : String
    {
        val msgBuilder = StringBuilder()

        if (cinema.name.isBlank())
            msgBuilder.append("Name can not be empty").append('\n')
        if (cinema.year <= 0)
            msgBuilder.append("Invalid year value").append('\n')
        if (cinema.director.isBlank())
            msgBuilder.append("Director can not be empty").append('\n')
        if (cinema.company.isBlank())
            msgBuilder.append("Company can not be empty").append('\n')

        return msgBuilder.toString()
    }

    private fun closeActivity()
    {
        setResult(RESULT_OK)
        finish()
    }
    private fun updateButtonAlertPositiveButtonCallback()
    {
        val msg = getMessage(mBinding.viewModel!!)

        if (msg.isBlank()) {
            CinemaRepository.update(mBinding.viewModel!!.cinema)
            closeActivity()
        }
        else
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }


    private fun modifySwitchCheckedChangeCallback(checked: Boolean)
    {
        mBinding.cinemaDetailsActivityButtonUpdate.isEnabled = checked
        mBinding.cinemaDetailsActivityButtonDelete.isEnabled = checked
    }

    private fun initModifySwitch()
    {
        mBinding.cinemaDetailsActivitySwitchModify.setOnCheckedChangeListener {_, checked -> modifySwitchCheckedChangeCallback(checked)}
    }


    private fun initTypeSpinner()
    {
        val values = CinemaType.values()
        val types = values.map{it.resourceName}.map{resources.getString(it)}.toTypedArray()

        mBinding.cinemaDetailsActivitySpinnerType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        mBinding.cinemaDetailsActivitySpinnerType.setSelection(mBinding.viewModel!!.type)
    }

    private fun initViews()
    {
        initTypeSpinner()
        initModifySwitch()
    }

    private fun initCinema()
    {
        mBinding.viewModel!!.cinema = if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra(CINEMA) as Cinema
        else
            intent.getSerializableExtra(CINEMA, Cinema::class.java)!!
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_cinema_details)
        mBinding.viewModel = CinemaDetailsActivityViewModel(this)
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

    fun updateButtonClickCallback()
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.message_title_cinema_details)
            .setMessage(R.string.message_text_cinema_details)
            .setPositiveButton(R.string.message_text_cinema_details_yes_button) {_, _ -> updateButtonAlertPositiveButtonCallback()}
            .setNegativeButton(R.string.message_text_cinema_details_no_button) {_, _-> ;}
            .create().show();
    }

    fun deleteButtonClickCallback()
    {
        TODO("Write that method")
    }
}