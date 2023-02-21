package com.erbaris.android.library.geonames.postalcode.data.entity

import java.time.LocalDateTime

data class PostalCodeInfo(var code : Int = 0,
                          var queryCount: Long = 1,
                          var queryDateTime : LocalDateTime = LocalDateTime.now(),
                          var saveDateTime: LocalDateTime = LocalDateTime.now(),
                          var sourceService: String = "csd")