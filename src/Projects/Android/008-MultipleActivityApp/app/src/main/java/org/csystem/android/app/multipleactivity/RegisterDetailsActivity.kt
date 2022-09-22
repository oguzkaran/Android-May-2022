package org.csystem.android.app.multipleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.csystem.android.app.multipleactivity.data.RegisterInfo
import org.csystem.android.app.multipleactivity.keys.REGISTER_INFO

class RegisterDetailsActivity : AppCompatActivity() {
    private fun initViews()
    {
        val ri = intent.getSerializableExtra(REGISTER_INFO) //Bu deprecated method versiyon kontrolü ile değiştirilecek

        Toast.makeText(this, ri.toString(), Toast.LENGTH_LONG).show()
    }

    private fun initialize()
    {
        setContentView(R.layout.activity_register_details)
        initViews()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}