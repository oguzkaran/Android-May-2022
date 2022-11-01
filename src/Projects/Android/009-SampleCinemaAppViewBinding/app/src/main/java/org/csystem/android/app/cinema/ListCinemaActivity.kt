package org.csystem.android.app.cinema

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.csystem.android.app.cinema.data.entity.Cinema
import org.csystem.android.app.cinema.databinding.ActivityListCinemaBinding
import org.csystem.android.app.cinema.keys.CINEMA

const val CINEMA_DETAILS_ACTIVITY_REQUEST_CODE = 1
class ListCinemaActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityListCinemaBinding

    private fun cinemaListViewItemClickCallback(pos: Int) {
        Intent(this, CinemaDetailsActivity::class.java).apply {
            putExtra(
                CINEMA,
                mBinding.listCinemaActivityListViewCinema.getItemAtPosition(pos) as Cinema
            )
            startActivityForResult(this, CINEMA_DETAILS_ACTIVITY_REQUEST_CODE)
        }
    }

    private fun initCinemaListView()
    {
        val list = CinemaRepository.findAll()

        if (list.isEmpty()) { //Diğer activity'ye alacağız
            AlertDialog.Builder(this)
                .setTitle(R.string.message_title_no_cinema)
                .setMessage(R.string.message_text_no_cinema)
                .setPositiveButton(R.string.message_text_no_cinema_ok_button) {_, _ -> finish()}
                .create().show()
        }
        else {
            mBinding.listCinemaActivityListViewCinema.adapter =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, CinemaRepository.findAll()) //findAll çağırmak çok fazla verinin olduğuh durumda dezavantaj olabilir
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

    @Deprecated("Since API Level 30")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if (requestCode == CINEMA_DETAILS_ACTIVITY_REQUEST_CODE)
            if (resultCode == RESULT_OK)
                mBinding.listCinemaActivityListViewCinema.adapter =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, CinemaRepository.findAll()) //findAll çağırmak çok fazla verinin olduğuh durumda dezavantaj olabilir
            else if (resultCode == RESULT_CANCELED)
                Toast.makeText(this, "Canceled", Toast.LENGTH_LONG).show()

        super.onActivityResult(requestCode, resultCode, data)
    }
}