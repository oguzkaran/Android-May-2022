package org.csystem.android.app.cinema

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.cinema.data.CinemaType
import org.csystem.android.app.cinema.data.entity.Cinema
import org.csystem.android.app.cinema.databinding.ActivityMainBinding
import org.csystem.android.app.cinema.keys.ALL

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding


    private fun addButtonClickCallback()
    {
        Intent(this, AddCinemaActivity::class.java).apply { startActivity(this) }
    }

    private fun exitButtonClickCallback()
    {
        Toast.makeText(this, R.string.message_text_goodbye_csd, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun initButtons()
    {
        mBinding.mainActivityButtonInsert.setOnClickListener { addButtonClickCallback() }
        mBinding.mainActivityButtonExit.setOnClickListener { exitButtonClickCallback() }
    }

    private fun initViews()
    {
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