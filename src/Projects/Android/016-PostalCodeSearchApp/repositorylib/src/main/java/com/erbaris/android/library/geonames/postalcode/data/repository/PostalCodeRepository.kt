package com.erbaris.android.library.geonames.postalcode.data.repository

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint
import java.util.*
import javax.inject.Inject

private const val POSTAL_CODE_ID = "postal_code_id";
private const val CODE = "code";
private const val ADMIN_CODE1 = "admin_code1";
private const val LONGITUDE = "longitude";
private const val COUNTRY_CODE = "country_code";
private const val ADMIN_NAME1 = "admin_name1";
private const val PLACE_NAME = "place_name";
private const val LATITUDE = "latitude";
private const val ISO_31662_INFO = "iso_31662_Info";

private const val TABLE_NAME = "postal_codes"

class PostalCodeRepository @Inject constructor() : IPostalCodeRepository {
    @Inject
    lateinit var db: SQLiteDatabase

    override fun <S : PostalCode?> save(postalCode: S): S
    {
        val cv = ContentValues();

        cv.put(CODE, postalCode?.code)
        cv.put(ADMIN_CODE1, postalCode?.adminCode1)
        cv.put(LONGITUDE, postalCode?.longitude)
        cv.put(COUNTRY_CODE, postalCode?.countryCode)
        cv.put(ADMIN_NAME1, postalCode?.adminName1)
        cv.put(PLACE_NAME, postalCode?.placeName)
        cv.put(LATITUDE, postalCode?.latitude)
        cv.put(ISO_31662_INFO, postalCode?.iSO31662Info)

        val postalCodeId = db.insert(TABLE_NAME, null, cv);

        if (postalCodeId == -1L)
            throw SQLiteException("PostalCodeRepository.save")

        return postalCode.also { it?.id = postalCodeId }
    }

    override fun findByCode(code: Int): MutableIterable<PostalCode>
    {
        TODO("Write this function")
    }

    //Not implemented methods
    override fun count(): Long
    {
        TODO("Not yet implemented")
    }

    override fun delete(entity: PostalCode?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll()
    {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<PostalCode>?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>?)
    {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int?)
    {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Int?): Boolean
    {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableIterable<PostalCode>
    {
        TODO("Not yet implemented")
    }

    override fun findAllById(id: MutableIterable<Int>?): MutableIterable<PostalCode>
    {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int?): Optional<PostalCode>
    {
        TODO("Not yet implemented")
    }



    override fun <S : PostalCode?> saveAll(entities: MutableIterable<S>?): MutableIterable<S>
    {
        TODO("Not yet implemented")
    }

}