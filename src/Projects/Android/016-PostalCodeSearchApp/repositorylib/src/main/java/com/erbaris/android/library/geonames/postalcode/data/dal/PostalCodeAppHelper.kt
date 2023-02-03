package com.erbaris.android.library.geonames.postalcode.data.dal

import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCodeInfo
import com.erbaris.android.library.geonames.postalcode.data.repository.IPostalCodeInfoRepository
import com.erbaris.android.library.geonames.postalcode.data.repository.IPostalCodeRepository
import com.karandev.util.data.repository.exception.RepositoryException
import java.sql.SQLException
import javax.inject.Inject

class PostalCodeAppHelper @Inject constructor() {
    @Inject
    lateinit var postalCodeRepository: IPostalCodeRepository

    @Inject
    lateinit var postalCodeInfoRepository: IPostalCodeInfoRepository

    fun savePostalCode(postalCodeInfo: PostalCodeInfo, postalCodes: Iterable<PostalCode>) : Boolean
    {
        try {
            if (postalCodeInfoRepository.existsById(postalCodeInfo.code))
                return false

            postalCodeInfoRepository.save(postalCodeInfo)
            postalCodes.forEach{postalCodeRepository.save(it)  }

            return true
        }
        catch (ex: SQLException) {
            throw RepositoryException("PostalCodeAppHelper.savePostalCode", ex)
        }
    }

    fun findPostalCodesByCode(code: Int): MutableIterable<PostalCode>
    {
        try {
            return postalCodeRepository.findByCode(code)
        }
        catch (ex: Exception) {
            throw RepositoryException("PostalCodeAppHelper.findPostalCodesByCode", ex)
        }
    }
    //...
}