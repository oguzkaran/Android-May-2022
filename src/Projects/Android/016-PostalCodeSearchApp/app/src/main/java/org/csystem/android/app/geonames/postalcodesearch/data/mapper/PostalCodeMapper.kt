package org.csystem.android.app.geonames.postalcodesearch.data.mapper

import org.csystem.android.app.geonames.postalcodesearch.data.entity.PostalCode
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeDTO
import javax.inject.Inject

class PostalCodeMapper @Inject constructor() : IPostalCodeMapper {
    override fun toPostalCodeDTO(postalCode: PostalCode) : PostalCodeDTO
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