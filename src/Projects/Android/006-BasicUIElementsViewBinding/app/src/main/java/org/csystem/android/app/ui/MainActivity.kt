package org.csystem.android.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import org.csystem.android.app.ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding
    private var mTextViewHitCounter: Int = 0

    private fun okButtonClickedCallback()
    {
        if (!mBinding.mainActivityCheckBoxAccept.isChecked) {
            Toast.makeText(this, R.string.must_accept_text, Toast.LENGTH_SHORT).show()
            return
        }
        val message = StringBuilder(mBinding.mainActivityEditTextMessage.text.toString())

        if (message.isBlank()) {
            Toast.makeText(this, R.string.is_blank_text, Toast.LENGTH_SHORT).show()
            return
        }

        if (mBinding.mainActivityEditTextOptionalMessage.visibility == View.VISIBLE)
            message.append(mBinding.mainActivityEditTextOptionalMessage.text.toString())

        if (mBinding.mainActivitySwitchReverse.isChecked)
            message.reverse()

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        "Message:$message".apply {
            mBinding.mainActivityTextViewMessage.text = this
        }
    }

    private fun exitButtonClickedCallback() = finish()

    private fun setOperationViewsEnabled(enabled: Boolean)
    {
        val layout = mBinding.mainActivityLinearLayoutOperation

        for (i in 0 until layout.childCount)
            layout.getChildAt(i).isEnabled = enabled
    }

    private fun checkBoxAcceptCheckedChangeCallback(checked: Boolean)
    {
        mBinding.mainActivityEditTextMessage.isEnabled = checked
        mBinding.mainActivityEditTextOptionalMessage.isEnabled = checked
    }

    private fun switchOptionalMessageCheckedChangeCallback(checked: Boolean)
    {
        mBinding.mainActivityEditTextOptionalMessage.visibility = if (checked) View.VISIBLE else View.GONE
    }

    private fun switchEnableCheckedChangeCallback(checked: Boolean) = setOperationViewsEnabled(checked)

    private fun toggleOpenCheckedChangeCallback(checked: Boolean)
    {
        val layoutOperation = mBinding.mainActivityLinearLayoutOperation
        val layoutChecks = mBinding.mainActivityLinearLayoutChecks

        if (checked) {
            layoutOperation.visibility = View.VISIBLE
            layoutChecks.visibility = View.VISIBLE
        }
        else {
            layoutOperation.visibility = View.INVISIBLE
            layoutChecks.visibility = View.INVISIBLE
        }
    }

    private fun initOpenToggleButton()
    {
       mBinding.mainActivityToggleButtonOpen.setOnCheckedChangeListener { _, checked ->  toggleOpenCheckedChangeCallback(checked)}
    }

    private fun initLayouts()
    {
        mBinding.mainActivityLinearLayoutOperation.visibility = View.INVISIBLE
        mBinding.mainActivityLinearLayoutChecks.visibility = View.INVISIBLE
    }

    private fun initOptionalMessageSwitch()
    {
        mBinding.mainActivitySwitchOptionalMessage.setOnCheckedChangeListener { _, checked ->  switchOptionalMessageCheckedChangeCallback(checked)}
    }

    private fun initEnableSwitch()
    {
        mBinding.mainActivitySwitchEnable.setOnCheckedChangeListener { _, checked ->  switchEnableCheckedChangeCallback(checked)}
    }

    private fun initAcceptCheckBox()
    {
        mBinding.mainActivityCheckBoxAccept.setOnCheckedChangeListener { _, checked ->  checkBoxAcceptCheckedChangeCallback(checked)}
    }

    private fun initMessageTextView()
    {
        "Message:".apply {
            mBinding.mainActivityTextViewMessage.text = this
        }
    }

    private fun initOKButton()
    {
        mBinding.mainActivityButtonOK.setOnClickListener {okButtonClickedCallback()}
    }

    private fun initExitButton()
    {
        mBinding.mainActivityButtonExit.setOnClickListener {exitButtonClickedCallback()}
    }

    private fun initViews()
    {
        initOpenToggleButton()
        initLayouts()
        initMessageTextView()
        initAcceptCheckBox()
        initEnableSwitch()
        initOptionalMessageSwitch()
        initOKButton()
        initExitButton()
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

    fun onTextViewMessageClicked(view: View)
    {
        ++mTextViewHitCounter
        title = "Counter:$mTextViewHitCounter"
    }


}