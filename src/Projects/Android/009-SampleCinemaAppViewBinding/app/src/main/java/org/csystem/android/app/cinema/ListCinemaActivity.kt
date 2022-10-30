package org.csystem.android.app.cinema

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.cinema.data.entity.Cinema
import org.csystem.android.app.cinema.databinding.ActivityListCinemaBinding
import org.csystem.android.app.cinema.keys.CINEMA

class ListCinemaActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityListCinemaBinding

    private fun cinemaListViewItemClickCallback(pos: Int) {
        Intent(this, CinemaDetailsActivity::class.java).apply {
            putExtra(
                CINEMA,
                mBinding.listCinemaActivityListViewCinema.getItemAtPosition(pos) as Cinema
            )
            startActivity(this)
        }
    }

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
        else {
            mBinding.listCinemaActivityListViewCinema.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, CinemaRepository.findAll())
            mBinding.listCinemaActivityListViewCinema.setOnItemClickListener{_, _, pos, _ -> cinemaListViewItemClickCallback(pos)}
        }
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