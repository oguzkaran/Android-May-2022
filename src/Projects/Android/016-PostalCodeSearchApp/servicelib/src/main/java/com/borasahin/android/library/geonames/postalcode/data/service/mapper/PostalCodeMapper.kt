package com.borasahin.android.library.geonames.postalcode.data.service.mapper

import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeSaveDTO
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode
import javax.inject.Inject

class PostalCodeMapper @Inject constructor() : IPostalCodeMapper {
    override fun toPostalCodeDTO(postalCode: PostalCode): PostalCodeDTO
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

    override fun toPostalCode(postalCodeSaveDTO: PostalCodeSaveDTO): PostalCode
    {
        return PostalCode(). apply {
            code = postalCodeSaveDTO.code.toInt()
            adminCode1 = postalCodeSaveDTO.adminCode1
            adminCode2 = postalCodeSaveDTO.adminCode2
            adminName1 = postalCodeSaveDTO.adminName1
            adminName2 = postalCodeSaveDTO.adminName2
            longitude = postalCodeSaveDTO.longitude
            latitude = postalCodeSaveDTO.latitude
            plate = postalCodeSaveDTO.plate
            placeName = postalCodeSaveDTO.placeName
        }
    }


}
