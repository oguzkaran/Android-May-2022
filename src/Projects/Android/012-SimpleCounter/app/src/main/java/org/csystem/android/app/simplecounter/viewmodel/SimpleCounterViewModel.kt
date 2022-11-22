package org.csystem.android.app.simplecounter.viewmodel

import org.csystem.android.app.simplecounter.MainActivity

data class SimpleCounterViewModel(var activity: MainActivity) {
    fun handleStartStopButton() = activity.startStopButtonClicked()
    fun handleStartButton() = activity.startButtonClicked()
    fun handleStopButton() = activity.stopButtonClicked()
}