package com.borasahin.android.library.geonames.postalcode.data.service

import com.borasahin.android.library.geonames.postalcode.data.service.mapper.IPostalCodeMapper
import com.erbaris.android.library.geonames.postalcode.data.dal.PostalCodeAppHelper
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCodeInfo
import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeDTO
import org.csystem.android.library.geonames.postalcode.dto.PostalCodeSaveDTO
import javax.inject.Inject

class PostalCodeAppService @Inject constructor() {
    @Inject
    lateinit var postalCodeAppHelper: PostalCodeAppHelper

    @Inject
    lateinit var postalCodeMapper: IPostalCodeMapper

    fun savePostalCode(postalCodeSaveDTOs: List<PostalCodeSaveDTO>) : Boolean
    {
        try {
            if (postalCodeSaveDTOs.isEmpty())
                return false;

            val code = postalCodeSaveDTOs[0].code;
            val list = postalCodeSaveDTOs.map { postalCodeMapper.toPostalCode(it) }.toList();

            return postalCodeAppHelper.savePostalCode(PostalCodeInfo(code), list)
        }
        catch (ex: RepositoryException) {
            throw DataServiceException("PostalCodeAppService.savePostalCode", ex.cause)
        }
    }

    fun findPostalCodesByCode(code: Int): MutableIterable<PostalCodeDTO>
    {
        try {
            return postalCodeAppHelper.findPostalCodesByCode(code).map { postalCodeMapper.toPostalCodeDTO(it) }.toMutableList()
        }
        catch (ex: RepositoryException) {
            throw DataServiceException("PostalCodeAppService.savePostalCode", ex.cause)
        }
    }
}