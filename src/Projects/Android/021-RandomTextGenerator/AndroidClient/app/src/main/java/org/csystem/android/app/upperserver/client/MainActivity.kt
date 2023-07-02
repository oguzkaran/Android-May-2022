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
        val sb = StringBuilder(if (mBinding.append) mBinding.result else "")

        for (i in 1..count)
            sb.append(TcpUtil.receiveStringViaLength(socket)).append('\n')

        //Add texts to list view by using adapter binding
        mBinding.result = sb.toString()
    }

    private fun checkCountMinimumAndBound(count:Int, min: Int, bound: Int) : Boolean
    {
        if (count > 0 && min >= 0 && bound > min)
            return true

        runOnUiThread { Toast.makeText(this, "$count and $bound must be greater than zero, $min must be non-negative", Toast.LENGTH_LONG).show() }

        return false
    }

    private fun checkCountViaServer(socket: Socket, count: Int): Boolean
    {
        if (TcpUtil.receiveInt(socket) != 0)
            return true

        val maxCount = TcpUtil.receiveInt(socket)
        val message = "$count is invalid for server\nMaximum value of count:$maxCount"
        runOnUiThread { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }

        return false
    }

    private fun checkMinimumAndBoundViaServer(socket: Socket, min: Int, bound: Int) : Boolean
    {
        if (TcpUtil.receiveInt(socket) != 0)
            return true

        val minValue = TcpUtil.receiveInt(socket)
        val boundValue = TcpUtil.receiveInt(socket)
        val message = "$min and/or $bound are invalid for server\nMinumum value:$minValue\nBound Value:$boundValue"
        runOnUiThread { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }

        return false
    }

    private fun randomTextGeneratorCallback()
    {
        try {
            val count = mBinding.countStr!!.toInt();
            val min = mBinding.minStr!!.toInt()
            val bound = mBinding.boundStr!!.toInt()
            if (!checkCountMinimumAndBound(count, min, bound))
                return

            Socket(mBinding.host, PORT).use {
                TcpUtil.sendInt(it, count)

                if (!checkCountViaServer(it, count))
                    return

                TcpUtil.sendInt(it, min)
                TcpUtil.sendInt(it, bound)

                if (!checkMinimumAndBoundViaServer(it, min, bound))
                    return

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
        mBinding.minStr = "5"
        mBinding.boundStr = "11"
        mBinding.result = ""
        mBinding.append = true
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