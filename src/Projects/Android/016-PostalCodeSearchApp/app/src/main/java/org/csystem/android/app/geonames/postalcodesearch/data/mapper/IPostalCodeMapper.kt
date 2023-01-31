package org.csystem.android.app.geonames.postalcodesearch.data.mapper

import org.csystem.android.app.geonames.postalcodesearch.data.entity.PostalCode
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeDTO

interface IPostalCodeMapper {
    fun toPostalCodeDTO(postalCode: PostalCode) : PostalCodeDTO
}