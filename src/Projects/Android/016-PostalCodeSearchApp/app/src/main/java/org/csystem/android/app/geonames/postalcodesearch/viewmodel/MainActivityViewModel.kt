package org.csystem.android.app.geonames.postalcodesearch.viewmodel

import org.csystem.android.app.geonames.postalcodesearch.MainActivity

data class MainActivityViewModel(var activity: MainActivity, var code: String = "") {
    fun handleFindAllPlacesButton() = activity.findAllPlacesButtonClicked()
}