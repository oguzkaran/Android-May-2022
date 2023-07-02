package org.csystem.android.app.upperserver.client.viewmodel

import org.csystem.android.app.upperserver.client.MainActivity

class MainActivityViewModel(val activity: MainActivity) {
    fun handleUpperButton() = activity.onUpperButtonClicked()
    fun handleExitButton() = activity.onExitButtonClicked()
}