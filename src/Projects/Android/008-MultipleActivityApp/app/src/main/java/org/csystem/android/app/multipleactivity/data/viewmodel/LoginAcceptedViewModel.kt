package org.csystem.android.app.multipleactivity.data.viewmodel

import android.content.Intent
import org.csystem.android.app.multipleactivity.LoginAcceptedActivity
import org.csystem.android.app.multipleactivity.LoginActivity
import org.csystem.android.app.multipleactivity.UsersActivity

class LoginAcceptedViewModel(val activity: LoginAcceptedActivity) {
    fun handleListUsersButtonClicked()
    {
        Intent(activity, UsersActivity::class.java).apply { activity.startActivity(this) }
    }

    fun handleBackButtonClicked()
    {
        activity.loginAcceptedActivityButtonBackCallback()
    }
}