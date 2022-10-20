package org.csystem.android.app.multipleactivity.data.viewmodel

import org.csystem.android.app.multipleactivity.RegisterActivity
import org.csystem.android.app.multipleactivity.data.Education
import org.csystem.android.app.multipleactivity.data.MaritalStatus
import java.io.Serializable
import java.time.LocalDate

data class RegisterInfoViewModel(var name: String = "", var email: String = "", var username: String = "",
                                 var password: String = "", var confirmPassword: String = "", var showPassword: Boolean = false,
                                 var education: Education = Education.GRADUATE, var maritalStatus: MaritalStatus = MaritalStatus.SINGLE,
                                 var birthDate: LocalDate = LocalDate.now(),
                                 var registerDate: LocalDate = LocalDate.now(),
                                 var daySelectedPosition: Int = 0, var monthSelectedPosition: Int = 0,
                                 var yearSelectedPosition: Int = 0
) : Serializable {
    @Transient
    lateinit var activity: RegisterActivity

    fun handleRegisterButtonClicked()
    {
        activity.registerButtonClickedCallback()
    }

    fun educationRadioGroupClicked(id: Int)
    {
        activity.educationRadioGroupClickedCallback(id)
    }

    fun maritalStatusRadioGroupClicked(id: Int)
    {
        activity.maritalStatusRadioGroupClickedCallback(id)
    }

    override fun toString() = "$username[$name]"
}