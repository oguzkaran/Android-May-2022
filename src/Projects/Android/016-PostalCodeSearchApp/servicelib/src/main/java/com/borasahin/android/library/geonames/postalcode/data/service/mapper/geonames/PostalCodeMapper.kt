package com.borasahin.android.library.geonames.postalcode.data.service.mapper.geonames

import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeSaveDTO
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity.PostalCode
import javax.inject.Inject

class PostalCodeMapper @Inject constructor() : IPostalCodeMapper {
    override fun toPostalCodeDTO(postalCode: PostalCode) : PostalCodeDTO
    {
        return PostalCodeDTO().apply {
            adminCode1 = postalCode.adminCode1
            adminCode2 = postalCode.adminCode2
            adminName1 = postalCode.adminName1
            adminName2 = postalCode.adminName2
            longitude = postalCode.longitude
            latitude = postalCode.latitude
            plate = postalCode.plate
            placeName = postalCode.placeName
        }
    }

    override fun toPostalCodeSaveDTO(postalCode: PostalCode): PostalCodeSaveDTO
    {
        return PostalCodeSaveDTO().apply {
            code = postalCode.code.toInt()
            adminCode1 = postalCode.adminCode1
            adminCode2 = postalCode.adminCode2
            adminName1 = postalCode.adminName1
            adminName2 = postalCode.adminName2
            longitude = postalCode.longitude
            latitude = postalCode.latitude
            plate = postalCode.plate
            placeName = postalCode.placeName
        }
    }
}