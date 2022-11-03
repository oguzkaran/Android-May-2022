package org.csystem.android.app.cinema

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.cinema.databinding.ActivityMainBinding
import org.csystem.android.app.cinema.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

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

    fun addButtonClickCallback()
    {
        Intent(this, AddCinemaActivity::class.java).apply { startActivity(this) }
    }

    fun listButtonClickCallback()
    {
        TODO("Write that method")
    }

    fun exitButtonClickCallback()
    {
        Toast.makeText(this, R.string.message_text_goodbye_csd, Toast.LENGTH_LONG).show()
        finish()
    }
}