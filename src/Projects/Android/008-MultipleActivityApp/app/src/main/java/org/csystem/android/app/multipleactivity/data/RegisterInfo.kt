package org.csystem.android.app.multipleactivity.data

import java.io.Serializable
import java.time.LocalDate

data class RegisterInfo(var name: String, var email: String, var username: String, var password: String,
                        var education: Education, var maritalStatus: MaritalStatus,
                        var birthDate: LocalDate = LocalDate.now(),
                        var registerDate: LocalDate = LocalDate.now()) : Serializable {
    override fun toString() = "$username[$name]"

}