package org.csystem.app.startanother.shareddatalib

import java.time.LocalDateTime
import java.io.Serializable

data class StringOperationInfo(var str: String,
                               var operation: StringOperation,
                               var dateTime : LocalDateTime = LocalDateTime.now(),

) : Serializable
