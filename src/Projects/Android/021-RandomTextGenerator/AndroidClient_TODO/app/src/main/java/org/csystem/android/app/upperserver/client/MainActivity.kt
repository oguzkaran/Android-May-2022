package org.csystem.android.app.upperserver.client

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karandev.util.net.TcpUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.upperserver.client.databinding.ActivityMainBinding
import org.csystem.android.app.upperserver.client.global.HOST
import org.csystem.android.app.upperserver.client.global.PORT
import org.csystem.android.app.upperserver.client.viewmodel.MainActivityViewModel
import java.io.IOException
import java.net.Socket
import java.util.concurrent.ExecutorService
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    @Inject
    lateinit var threadPool : ExecutorService

    private fun receiveTexts(socket: Socket, count: Int)
    {
        val sb = StringBuilder()

        for (i in 1..count)
            sb.append(TcpUtil.receiveStringViaLength(socket)).append('\n')

        //Add texts to list view by using adapter binding
        mBinding.result = sb.toString()
    }

    private fun randomTextGeneratorCallback()
    {
        mBinding.result = ""

        try {
            val count = mBinding.countStr!!.toInt();
            val min = mBinding.minStr!!.toInt()
            val bound = mBinding.boundStr!!.toInt()
            if (count <= 0 || min < 0 || bound <= 0) {
                runOnUiThread { Toast.makeText(this, "$count and $bound must be greater than zero, $min must be non-negative", Toast.LENGTH_LONG).show() }
                return;
            }

            Socket(mBinding.host, PORT).use {
                TcpUtil.sendInt(it, count)
                if (TcpUtil.receiveInt(it) == 0) {
                    runOnUiThread { Toast.makeText(this, "$count is invalid for server", Toast.LENGTH_LONG).show() }
                    return
                }

                TcpUtil.sendInt(it, min)
                TcpUtil.sendInt(it, bound)

                if (TcpUtil.receiveInt(it) == 0) {
                    runOnUiThread { Toast.makeText(this, "$min and/or $bound are invalid for server", Toast.LENGTH_LONG).show() }
                    return
                }

                receiveTexts(it, count)
            }
        }
        catch (ignore: NumberFormatException) {
            runOnUiThread { Toast.makeText(this, "Invalid number!...", Toast.LENGTH_LONG).show() }
        }
        catch (ex: IOException) {
            runOnUiThread {Toast.makeText(this, "Problem occurs while send/receive", Toast.LENGTH_LONG).show()}
        }
        catch (ex: Throwable) {
            runOnUiThread {Toast.makeText(this, "General problem occurs. Try again later", Toast.LENGTH_LONG).show()}
        }
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
        mBinding.host = HOST
        mBinding.countStr = "10"
        mBinding.minStr = "0"
        mBinding.boundStr = "0"
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

    fun onUpperButtonClicked() = threadPool.execute {randomTextGeneratorCallback()}

    fun onExitButtonClicked() = finish()
}