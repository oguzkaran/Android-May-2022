package com.borasahin.android.library.geonames.postalcode.data.service.mapper

import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeDTO
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeSaveDTO

interface IPostalCodeMapper {
    //fun toPostalCodeSaveDTO(postalCode: PostalCode) : PostalCodeSaveDTO
    fun toPostalCode(postalCodeSaveDTO: PostalCodeSaveDTO) : PostalCode
    fun toPostalCodeDTO(postalCode: PostalCode) : PostalCodeDTO
}