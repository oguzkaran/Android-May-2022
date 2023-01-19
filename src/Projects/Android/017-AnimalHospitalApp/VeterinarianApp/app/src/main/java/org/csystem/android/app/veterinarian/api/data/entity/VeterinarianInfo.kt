package org.csystem.android.app.veterinarian.api.data.entity

data class VeterinarianInfo(
                        var diplomaNo: Long,
                        var citizenId: String?,
                        var firstName: String?,
                        var middleName: String?,
                        var lastName: String?,
                        var registerDate: String?
) {
    override fun toString() = when  {
        middleName != null -> "$firstName $middleName ${lastName?.uppercase()}"
        else -> "$firstName ${lastName?.uppercase()}"
    }
}