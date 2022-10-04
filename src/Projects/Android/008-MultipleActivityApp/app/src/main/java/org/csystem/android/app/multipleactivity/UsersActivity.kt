package org.csystem.android.app.multipleactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import org.csystem.android.app.multipleactivity.data.UsersRepository
import org.csystem.android.app.multipleactivity.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityUsersBinding

    private fun initUsers()
    {
        val users = UsersRepository.findAll()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)

        mBinding.usersActivityListViewUsers.adapter = adapter
    }

    private fun initBinding()
    {
        mBinding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    private fun initialize()
    {
        initBinding()
        initUsers()
        setTitle(R.string.users_activity_title)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }
}