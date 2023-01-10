package org.csystem.android.app.veterinarian.viewmodel

import org.csystem.android.app.veterinarian.MainActivity

class MainActivityViewModel(var activity: MainActivity){
    fun handleSaveButton() = activity.saveButtonClicked()
    fun handleGetButton() = activity.getButtonClicked()
}