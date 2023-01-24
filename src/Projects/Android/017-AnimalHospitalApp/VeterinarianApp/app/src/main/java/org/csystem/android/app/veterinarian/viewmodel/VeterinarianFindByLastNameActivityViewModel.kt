package org.csystem.android.app.veterinarian.viewmodel

import org.csystem.android.app.veterinarian.VeterinarianFindByLastNameActivity
import org.csystem.android.app.veterinarian.api.data.entity.VeterinarianInfo
import org.csystem.android.app.veterinarian.viewmodel.adapter.VeterinarianListAdapter

data class VeterinarianFindByLastNameActivityViewModel(
    var activity: VeterinarianFindByLastNameActivity,
    var text: String = "",
    var adapter: VeterinarianListAdapter<VeterinarianInfo> = VeterinarianListAdapter(activity),
    var offline: Boolean = false) {
    fun handleFindButton() = activity.findButtonClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}