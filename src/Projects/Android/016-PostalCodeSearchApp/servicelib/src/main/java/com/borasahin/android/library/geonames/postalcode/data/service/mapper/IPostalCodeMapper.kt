package com.borasahin.android.library.geonames.postalcode.data.service.mapper

import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeSaveDTO
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode

interface IPostalCodeMapper {
    //fun toPostalCodeSaveDTO(postalCode: PostalCode) : PostalCodeSaveDTO
    fun toPostalCode(postalCodeSaveDTO: PostalCodeSaveDTO) : PostalCode
    fun toPostalCodeDTO(postalCode: PostalCode) : PostalCodeDTO
}