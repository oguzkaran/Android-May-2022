package org.csystem.android.app.veterinarian.viewmodel

import org.csystem.android.app.veterinarian.VeterinarianInfoActivity

class VeterinarianInfoActivityViewModel(var activity: VeterinarianInfoActivity) {
    fun handleCountButton() = activity.countButtonClicked()
    fun handleFindByLastNameButton() = activity.findByLastNameButtonClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}