package org.csystem.android.app.veterinarian.viewmodel

import org.csystem.android.app.veterinarian.VeterinarianFindByLastNameActivity

data class VeterinarianFindByLastNameActivityViewModel(
    var activity: VeterinarianFindByLastNameActivity,
    var text: String = ""
) {
    fun handleFindButton() = activity.findButtonClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}