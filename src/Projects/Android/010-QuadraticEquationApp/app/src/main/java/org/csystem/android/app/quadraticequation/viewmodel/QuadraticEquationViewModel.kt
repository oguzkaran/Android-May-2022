package org.csystem.android.app.quadraticequation.viewmodel

import org.csystem.android.app.quadraticequation.MainActivity

data class QuadraticEquationViewModel(var activity: MainActivity, var a: Double = 0.0, var b: Double = 0.0, var c: Double = 0.0) {
    fun handleSolveButton()
    {
        activity.solveButtonClicked()
    }
}