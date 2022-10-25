package org.csystem.android.app.cinema

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.cinema.databinding.ActivityListCinemaBinding

class ListCinemaActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityListCinemaBinding

    private fun initCinemaListView()
    {
        val list = CinemaRepository.findAll()
        if (list.isEmpty()) {
            AlertDialog.Builder(this)
                .setTitle(R.string.message_title_no_cinema)
                .setMessage(R.string.message_text_no_cinema)
                .setPositiveButton(R.string.message_text_no_cinema_ok_button) {_, _ -> finish()}
                .create().show()
        }
        else
            mBinding.listCinemaActivityListViewCinema.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, CinemaRepository.findAll())

    }

    private fun initBinding()
    {
        mBinding = ActivityListCinemaBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initCinemaListView()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}