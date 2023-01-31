package com.borasahin.android.library.geonames.postalcode.data.service.mapper

import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeDTO
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeSaveDTO
import javax.inject.Inject

class PostalCodeMapper @Inject constructor() : IPostalCodeMapper {
    override fun toPostalCode(postalCodeSaveDTO: PostalCodeSaveDTO): PostalCode
    {
        return PostalCode(). apply {
            code = postalCodeSaveDTO.code
            adminCode1 = postalCodeSaveDTO.adminCode1
            longitude = postalCodeSaveDTO.longitude
            countryCode = postalCodeSaveDTO.countryCode
            adminName1 = postalCodeSaveDTO.adminName1
            placeName = postalCodeSaveDTO.placeName
            latitude = postalCodeSaveDTO.latitude
            iSO31662Info = postalCodeSaveDTO.iSO31662Info
        }
    }

    override fun toPostalCodeDTO(postalCode: PostalCode): PostalCodeDTO
    {
        return PostalCodeDTO().apply {
            adminCode1 = postalCode.adminCode1
            longitude = postalCode.longitude
            countryCode = postalCode.countryCode
            adminName1 = postalCode.adminName1
            placeName = postalCode.placeName
            latitude = postalCode.latitude
            iSO31662Info = postalCode.iSO31662Info
        }
    }
}
