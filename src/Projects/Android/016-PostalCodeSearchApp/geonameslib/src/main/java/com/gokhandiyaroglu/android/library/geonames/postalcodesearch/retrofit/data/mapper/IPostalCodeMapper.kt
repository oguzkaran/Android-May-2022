package com.gokhandiyaroglu.android.app.geonames.postalcodesearch.data.mapper

import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity.PostalCode
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeDTO
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeSaveDTO

interface IPostalCodeMapper {
    fun toPostalCodeDTO(postalCode: PostalCode) : PostalCodeDTO
    fun toPostalCodeSaveDTO(postalCode: PostalCode) : PostalCodeSaveDTO
}