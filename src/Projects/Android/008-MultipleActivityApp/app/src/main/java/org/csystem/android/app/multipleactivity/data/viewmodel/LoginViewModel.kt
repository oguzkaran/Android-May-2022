package org.csystem.android.app.multipleactivity.data.viewmodel

import org.csystem.android.app.multipleactivity.LoginActivity

class LoginViewModel(val activity: LoginActivity) {
    fun handleLoginButtonClicked()
    {
        activity.loginButtonClickedCallback()
    }
}