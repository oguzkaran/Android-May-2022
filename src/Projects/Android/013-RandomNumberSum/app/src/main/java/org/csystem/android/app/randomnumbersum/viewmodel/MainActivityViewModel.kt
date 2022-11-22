package org.csystem.android.app.randomnumbersum.viewmodel

import org.csystem.android.app.randomnumbersum.MainActivity

data class MainActivityViewModel(var activity: MainActivity, var count: Int = 10, var min: Int = 0, var bound: Int = 100) {
    fun handleOkButtonClicked() = activity.okButtonClicked()
}