package org.csystem.android.app.cinema.viewmodel

import org.csystem.android.app.cinema.MainActivity

data class MainActivityViewModel constructor(private val activity: MainActivity) {
    fun handleAddButtonClicked() = activity.addButtonClickCallback()
    fun handleListButtonClicked() = activity.listButtonClickCallback()
    fun handleExitButtonClicked() = activity.exitButtonClickCallback()
}