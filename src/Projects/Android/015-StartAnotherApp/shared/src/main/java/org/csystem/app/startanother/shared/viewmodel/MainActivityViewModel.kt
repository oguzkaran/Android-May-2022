package org.csystem.app.startanother.shared.viewmodel

import org.csystem.app.startanother.shared.MainActivity
import org.csystem.app.startanother.shareddatalib.StringOperation
import org.csystem.app.startanother.shareddatalib.StringOperationInfo

class MainActivityViewModel(val activity: MainActivity,
                            val stringOperationInfo: StringOperationInfo = StringOperationInfo("", StringOperation.UPPER),
                            var type: Int = stringOperationInfo.operation.ordinal) {
    fun handleCalculateButton() = activity.calculateButtonClicked()
}