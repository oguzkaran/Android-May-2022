package org.csystem.android.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var mTextViewHitCounter: Int = 0
    private lateinit var mEditTextMessage: EditText
    private lateinit var mTextViewMessage: TextView
    private lateinit var mCheckBoxAccept: CheckBox
    private lateinit var mSwitchReverse: Switch

    private fun checkBoxAcceptCheckedChangeCallback(checked: Boolean)
    {
        Toast.makeText(this, if (checked) "Accepted" else "Rejected", Toast.LENGTH_SHORT).show()
    }

    private fun switchCheckedChangeCallback(checked: Boolean)
    {
        if (checked)
            Toast.makeText(this, "Reverse", Toast.LENGTH_SHORT).show()
    }

    private fun initReverseSwitch()
    {
        mSwitchReverse = findViewById(R.id.mainActivitySwitchReverse)
        mSwitchReverse.setOnCheckedChangeListener { _, checked ->  switchCheckedChangeCallback(checked)}
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

    private fun initMessageEditText()
    {
        mEditTextMessage = findViewById(R.id.mainActivityEditTextMessage) //Bu şekilde erişim kolaylaştırıldı. Sadece arka planı göstermek için yazıldı
    }

    private fun initViews()
    {
        initMessageEditText()
        initMessageTextView()
        initAcceptCheckBox()
        initReverseSwitch()
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
        Toast.makeText(this, "TextView Clicked", Toast.LENGTH_SHORT).show()
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

        if (mSwitchReverse.isChecked)
            message.reverse();

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        "Message:$message".apply {
            mTextViewMessage.text = this
        }
    }
}