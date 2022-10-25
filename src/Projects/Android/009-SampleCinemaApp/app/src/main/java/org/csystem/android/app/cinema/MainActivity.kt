package org.csystem.android.app.cinema

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.cinema.data.CinemaType
import org.csystem.android.app.cinema.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding


    private fun saveButtonClickCallback()
    {
        //...
    }

    private fun exitButtonClickCallback()
    {
        Toast.makeText(this, R.string.text_goodbye_csd, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initButtons()
    {
        mBinding.mainActivityButtonSave.setOnClickListener { saveButtonClickCallback() }
        mBinding.mainActivityButtonExit.setOnClickListener { exitButtonClickCallback() }
    }

    private fun initTypeSpinner()
    {
        val values = CinemaType.values()
        val types = values.map{it.resourceName}.map{resources.getString(it)}.toTypedArray()

        mBinding.mainActivitySpinnerType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
    }

    private fun initViews()
    {
        initTypeSpinner()
        initButtons()
    }

    private fun initBinding()
    {
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
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
}