package org.csystem.android.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var mTextViewHitCounter: Int = 0
    private lateinit var mEditTextMessage: EditText
    private lateinit var mTextViewMessage: TextView

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
        val message = mEditTextMessage.text.toString()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        "Message:$message".apply {
            mTextViewMessage.text = this
        }
    }
}