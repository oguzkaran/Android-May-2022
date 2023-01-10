package org.csystem.android.app.veterinarian.api.data.entity

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class VeterinarianSave(
                        var diplomaNo: Long = 0,
                        var citizenId: String? = null,
                        var firstName: String? = null,
                        var middleName: String? = null,
                        var lastName: String? = null,
                        var birthDate: String? = null,
                        var registerDate: String? =  DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now())
)
