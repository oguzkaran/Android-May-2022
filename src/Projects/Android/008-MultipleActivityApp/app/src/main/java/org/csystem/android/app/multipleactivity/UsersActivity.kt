package org.csystem.android.app.multipleactivity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import org.csystem.android.app.multipleactivity.data.RegisterInfo
import org.csystem.android.app.multipleactivity.data.UsersRepository
import org.csystem.android.app.multipleactivity.databinding.ActivityUsersBinding
import org.csystem.android.app.multipleactivity.keys.REGISTER_INFO

class UsersActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityUsersBinding

    private fun alertPositiveButtonProc(ri: RegisterInfo)
    {
        Intent(this, UserDetailsActivity::class.java).putExtra(REGISTER_INFO, ri).also { startActivity(it) }
    }

    private fun alertNegativeButtonProc(ri: RegisterInfo)
    {
        Toast.makeText(this, "No:${ri}", Toast.LENGTH_LONG).show()
    }

    private fun alertNeutralButtonProc(ri: RegisterInfo)
    {
        Toast.makeText(this, "Cancel:${ri}", Toast.LENGTH_LONG).show()
    }

    private fun alertButtonsCallback(which: Int, ri: RegisterInfo)
    {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> alertPositiveButtonProc(ri)
            DialogInterface.BUTTON_NEGATIVE -> alertNegativeButtonProc(ri)
            else -> alertNeutralButtonProc(ri)
        }
    }

    private fun showAlert(ri: RegisterInfo)
    {
        AlertDialog.Builder(this)
            .setMessage(R.string.alert_details_message)
            .setPositiveButton(R.string.alert_details_yes) {_, w -> alertButtonsCallback(w, ri)}
            .setNegativeButton(R.string.alert_details_no) {_, w -> alertButtonsCallback(w, ri)}
            .setNeutralButton(R.string.alert_details_cancel) {_, w -> alertButtonsCallback(w, ri)}
            .setIcon(R.drawable.question_mark_icon)
            .create().show()
    }

    private fun listViewUsersItemClickCallback(pos: Int) = showAlert(mBinding.usersActivityListViewUsers.getItemAtPosition(pos) as RegisterInfo)


    private fun initUsers()
    {
        val users = UsersRepository.findAll()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)

        mBinding.usersActivityListViewUsers.adapter = adapter
    }

    private fun initUsersListView()
    {
        initUsers()
        mBinding.usersActivityListViewUsers.setOnItemClickListener{_, _, pos, _ -> listViewUsersItemClickCallback(pos)}
    }


    private fun initViews()
    {
        initUsersListView()
    }

    private fun initBinding()
    {
        mBinding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initViews()
        setTitle(R.string.users_activity_title)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}