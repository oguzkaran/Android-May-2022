package org.csystem.android.app.veterinarian.viewmodel

import org.csystem.android.app.veterinarian.VeterinarianFindByLastNameActivity
import org.csystem.android.app.veterinarian.viewmodel.adapter.VeterinarianListAdapter

data class VeterinarianFindByLastNameActivityViewModel(
    var activity: VeterinarianFindByLastNameActivity,
    var text: String = "",
    var adapter: VeterinarianListAdapter = VeterinarianListAdapter(activity)
) {
    fun handleFindButton() = activity.findButtonClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}