package org.csystem.app.startanother.shared

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.app.startanother.shared.databinding.ActivityMainBinding
import org.csystem.app.startanother.shared.viewmodel.MainActivityViewModel
import org.csystem.app.startanother.shareddatalib.StringOperation
import org.csystem.app.startanother.shareddatalib.StringOperationProvider
import javax.inject.Inject

private val COMMANDS = arrayOf("UPPER", "LOWER", "REVERSE")

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    @Inject lateinit var stringOperationProvider: StringOperationProvider

    private fun doUpper()
    {
        mBinding.result = mBinding.viewModel!!.stringOperationInfo.str.uppercase()
    }

    private fun doLower()
    {
        mBinding.result = mBinding.viewModel!!.stringOperationInfo.str.lowercase()
    }

    private fun doReverse()
    {
        mBinding.result = StringBuilder(mBinding.viewModel!!.stringOperationInfo.str).reverse().toString()
    }

    private fun getCommand(commandStr: String) : Pair<Int, String>
    {
        val index = commandStr.indexOfAny(charArrayOf(' ', '\t'))

        //Toast.makeText(this, commandStr, Toast.LENGTH_LONG).show()
        //Toast.makeText(this, index.toString(), Toast.LENGTH_LONG).show()

        return Pair(index, commandStr.substring(0, index))
    }

    private fun parseCommand(commandStr: String) : Pair<Int, String>
    {
        //REVERSE Bugün hava çok güzel
        val (index, command) = getCommand(commandStr.trimStart())
        val cmdIndex = COMMANDS.indexOf(command)

        if (cmdIndex == -1)
            return Pair(-1, "")

        return Pair(cmdIndex, commandStr.substring(index + 1))
    }

    private fun parseData()
    {
        val commandStr = intent.getStringExtra("COMMAND")

        if (commandStr == null) {
            Toast.makeText(this, "Invalid Data", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val (index, text) = parseCommand(commandStr)

        if (index == -1) {
            Toast.makeText(this, "Invalid command", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        mBinding.viewModel!!.stringOperationInfo.str = text
        mBinding.viewModel!!.type = index
    }

    private fun initOperationSpinner()
    {
        mBinding.mainActivitySpinnerOperation.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stringOperationProvider.valuesStr())
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainActivityViewModel(this)
        mBinding.result = ""
    }

    private fun initialize()
    {
        initBinding()
        initOperationSpinner()
        try {
            parseData()
        }
        catch (ex: Throwable) {
            Toast.makeText(this, "Shared:${ex.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun calculateButtonClicked()
    {
        when (mBinding.viewModel!!.type) {
            0 -> doUpper()
            1 -> doLower()
            2 -> doReverse()
        }
    }
}