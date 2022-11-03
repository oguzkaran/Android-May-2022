package org.csystem.android.app.cinema

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.cinema.data.CinemaType
import org.csystem.android.app.cinema.databinding.ActivityAddCinemaBinding
import org.csystem.android.app.cinema.viewmodel.AddCinemaActivityViewModel

class AddCinemaActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityAddCinemaBinding

    private fun getMessage(cinema: AddCinemaActivityViewModel) : String
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

    private fun initTypeSpinner()
    {
        val values = CinemaType.values()
        val types = values.map{it.resourceName}.map{resources.getString(it)}.toTypedArray()

        mBinding.addCinemaActivitySpinnerType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
    }

    private fun initViews()
    {
        initTypeSpinner()
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_cinema)
        mBinding.viewModel = AddCinemaActivityViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun saveButtonClickCallback()
    {
        val msg = getMessage(mBinding.viewModel!!)

        if (msg.isBlank()) {
            CinemaRepository.save(mBinding.viewModel!!.cinema)
            Toast.makeText(this, "Count:${CinemaRepository.count()}", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun listButtonClickCallback()
    {
        if (CinemaRepository.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle(R.string.message_title_no_cinema)
                .setMessage(R.string.message_text_no_cinema)
                .setPositiveButton(R.string.message_text_no_cinema_ok_button) { _, _ ->  }
                .create().show()
        }
        else
            Intent(this, ListCinemaActivity::class.java).apply { startActivity(this) }
    }
}