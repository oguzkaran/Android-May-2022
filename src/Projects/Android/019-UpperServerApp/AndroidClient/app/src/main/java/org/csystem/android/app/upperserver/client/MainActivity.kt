package org.csystem.android.app.upperserver.client

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.csystem.android.app.upperserver.client.databinding.ActivityMainBinding
import org.csystem.android.app.upperserver.client.viewmodel.MainActivityViewModel
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private suspend fun upperCallback()
    {
        mBinding.result = ""

        try {
            Socket(mBinding.host, 50515).use {
                val bw = BufferedWriter(OutputStreamWriter(it.getOutputStream(), StandardCharsets.UTF_8))
                val br = BufferedReader(InputStreamReader(it.getInputStream(), StandardCharsets.UTF_8))

                bw.write(mBinding.text + "\r\n")
                bw.flush()
                mBinding.result = br.readLine().trim()
            }
        }
        catch (ex: IOException) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Problem occurs while send/receive", Toast.LENGTH_LONG).show()
            }
        }
        catch (ex: Throwable) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "General problem occurs. Try again later", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
        mBinding.host = "161.97.141.113"
        mBinding.text = ""
        mBinding.result = ""
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

    fun onUpperButtonClicked()
    {
        CoroutineScope(Dispatchers.IO).launch { upperCallback() }
    }

    fun onExitButtonClicked() = finish()
}