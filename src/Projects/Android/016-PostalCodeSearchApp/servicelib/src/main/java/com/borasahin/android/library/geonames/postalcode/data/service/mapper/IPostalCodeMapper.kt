package com.borasahin.android.library.geonames.postalcode.data.service.mapper

import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeSaveDTO
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity.PostalCode as PostalCodeGeonames

interface IPostalCodeMapper {
    fun toPostalCode(postalCodeSaveDTO: PostalCodeSaveDTO) : PostalCode
    fun toPostalCodeDTO(postalCode: PostalCode) : PostalCodeDTO
    fun toPostalCodeDTO(postalCode: PostalCodeGeonames) : PostalCodeDTO
}