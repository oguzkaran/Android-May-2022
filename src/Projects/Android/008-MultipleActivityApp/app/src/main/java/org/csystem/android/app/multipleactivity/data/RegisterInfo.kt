package org.csystem.android.app.multipleactivity.data

import java.io.Serializable

data class RegisterInfo(var name: String, var email: String, var username: String, var password: String,
                        var education: Education, var maritalStatus: MaritalStatus) : Serializable {
    override fun toString() = "$username[$name]"

}