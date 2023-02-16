package com.borasahin.android.library.geonames.postalcode.data.service.mapper.geonames

import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeSaveDTO
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity.PostalCode

interface IPostalCodeMapper {
    fun toPostalCodeDTO(postalCode: PostalCode) : PostalCodeDTO
    fun toPostalCodeSaveDTO(postalCode: PostalCode) : PostalCodeSaveDTO
}