package org.csystem.android.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var mTextViewHitCounter: Int = 0
    private lateinit var mToggleButtonOpen: ToggleButton
    private lateinit var mLinearLayoutOperation: LinearLayout
    private lateinit var mLinearLayoutChecks: LinearLayout
    private lateinit var mEditTextMessage: EditText
    private lateinit var mEditTextOptionalMessage: EditText
    private lateinit var mTextViewMessage: TextView
    private lateinit var mCheckBoxAccept: CheckBox
    private lateinit var mSwitchReverse: Switch
    private lateinit var mSwitchEnable: Switch
    private lateinit var mSwitchOptionalMessage: Switch

    private fun setOperationViewsEnabled(enabled: Boolean)
    {
        for (i in 0 until mLinearLayoutOperation.childCount)
            mLinearLayoutOperation.getChildAt(i).isEnabled = enabled
    }

    private fun checkBoxAcceptCheckedChangeCallback(checked: Boolean)
    {
        mEditTextMessage.isEnabled = checked
        mEditTextOptionalMessage.isEnabled = checked
    }

    private fun switchOptionalMessageCheckedChangeCallback(checked: Boolean)
    {
        mEditTextOptionalMessage.visibility = if (checked) View.VISIBLE else View.GONE
    }

    private fun switchEnableCheckedChangeCallback(checked: Boolean) = setOperationViewsEnabled(checked)

    private fun toggleOpenCheckedChangeCallback(checked: Boolean)
    {
        if (checked) {
            mLinearLayoutOperation.visibility = View.VISIBLE
            mLinearLayoutChecks.visibility = View.VISIBLE
        }
        else {
            mLinearLayoutOperation.visibility = View.INVISIBLE
            mLinearLayoutChecks.visibility = View.INVISIBLE
        }
    }

    private fun initOpenToggleButton()
    {
        mToggleButtonOpen = findViewById(R.id.mainActivityToggleButtonOpen) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
        mToggleButtonOpen.setOnCheckedChangeListener { _, checked ->  toggleOpenCheckedChangeCallback(checked)}
    }

    private fun initLayouts()
    {
        mLinearLayoutOperation = findViewById(R.id.mainActivityLinearLayoutOperation) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
        mLinearLayoutOperation.visibility = View.INVISIBLE
        mLinearLayoutChecks = findViewById(R.id.mainActivityLinearLayoutChecks) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
        mLinearLayoutChecks.visibility = View.INVISIBLE
    }

    private fun initOptionalMessageSwitch()
    {
        mSwitchOptionalMessage = findViewById(R.id.mainActivitySwitchOptionalMessage) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
        mSwitchOptionalMessage.setOnCheckedChangeListener { _, checked ->  switchOptionalMessageCheckedChangeCallback(checked)}
    }

    private fun initEnableSwitch()
    {
        mSwitchEnable = findViewById(R.id.mainActivitySwitchEnable) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
        mSwitchEnable.setOnCheckedChangeListener { _, checked ->  switchEnableCheckedChangeCallback(checked)}
    }

    private fun initReverseSwitch()
    {
        mSwitchReverse = findViewById(R.id.mainActivitySwitchReverse) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
    }

    private fun initAcceptCheckBox()
    {
        mCheckBoxAccept = findViewById(R.id.mainActivityCheckBoxAccept) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
        mCheckBoxAccept.setOnCheckedChangeListener { _, checked ->  checkBoxAcceptCheckedChangeCallback(checked)}
    }

    private fun initMessageTextView()
    {
        mTextViewMessage = findViewById(R.id.mainActivityTextViewMessage) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
        "Message:".apply {
            mTextViewMessage.text = this
        }
    }

    private fun initOptionalMessageEditText()
    {
        mEditTextOptionalMessage = findViewById(R.id.mainActivityEditTextOptionalMessage) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
    }

    private fun initMessageEditText()
    {
        mEditTextMessage = findViewById(R.id.mainActivityEditTextMessage) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
    }


    private fun initViews()
    {
        initOpenToggleButton()
        initLayouts()
        initMessageEditText()
        initOptionalMessageEditText()
        initMessageTextView()
        initAcceptCheckBox()
        initReverseSwitch()
        initEnableSwitch()
        initOptionalMessageSwitch()
    }

    private fun initialize()
    {
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    fun onTextViewMessageClicked(view: View)
    {
        ++mTextViewHitCounter
        title = "Counter:$mTextViewHitCounter"
    }

    fun onOKButtonClicked(view: View)
    {
        if (!mCheckBoxAccept.isChecked) {
            Toast.makeText(this, R.string.must_accept_text, Toast.LENGTH_SHORT).show()
            return
        }
        val message = StringBuilder(mEditTextMessage.text.toString())

        if (message.isBlank()) {
            Toast.makeText(this, R.string.is_blank_text, Toast.LENGTH_SHORT).show()
            return
        }

        if (mEditTextOptionalMessage.visibility == View.VISIBLE)
            message.append(mEditTextOptionalMessage.text.toString())

        if (mSwitchReverse.isChecked)
            message.reverse();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        "Message:$message".apply {
            mTextViewMessage.text = this
        }
    }

    fun onExitButtonClicked(view: View) = finish()
}