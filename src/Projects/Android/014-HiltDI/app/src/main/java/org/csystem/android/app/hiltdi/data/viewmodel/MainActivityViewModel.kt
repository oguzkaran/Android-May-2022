package org.csystem.android.app.hiltdi.data.viewmodel

import org.csystem.android.app.hiltdi.MainActivity

data class MainActivityViewModel(val activity: MainActivity) {
    fun handleFirstButton() = activity.firstButtonClicked()
    fun handleSecondtButton() = activity.secondButtonClicked()
}