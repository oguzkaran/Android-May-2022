package org.csystem.android.app.veterinarian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class VeterinarianFindByLastNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_veterinarinarian_find_by_last_name)
    }

    fun findButtonClicked()
    {

    }

    fun exitButtonClicked() = finish()
}