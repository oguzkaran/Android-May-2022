package org.csystem.android.app.veterinarian.viewmodel

import org.csystem.android.app.veterinarian.VeterinarianSaveActivity
import org.csystem.android.app.veterinarian.api.data.entity.VeterinarianSave

class VeterinarianSaveActivityViewModel(var activity: VeterinarianSaveActivity,
                                        var veterinarianSave: VeterinarianSave? = VeterinarianSave()) {
    fun handleSaveButton() = activity.saveButtonClicked()
    fun handleExitButton() = activity.exitButtonClicked()
}