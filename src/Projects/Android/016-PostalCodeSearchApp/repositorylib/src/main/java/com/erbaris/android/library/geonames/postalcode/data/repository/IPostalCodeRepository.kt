package com.erbaris.android.library.geonames.postalcode.data.repository

import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode
import com.karandev.util.data.repository.ICrudRepository

interface IPostalCodeRepository : ICrudRepository<PostalCode, Int> {
    fun findByCode(code: Int) : MutableIterable<PostalCode>
}