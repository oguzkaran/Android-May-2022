package com.erbaris.android.library.geonames.postalcode.data.repository

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

private const val POSTAL_CODE_ID = "postal_code_id";
private const val CODE = "code";
private const val ADMIN_CODE1 = "admin_code1";
private const val ADMIN_CODE2 = "admin_code2";
private const val ADMIN_NAME1 = "admin_name1";
private const val ADMIN_NAME2 = "admin_name2";
private const val LONGITUDE = "longitude";
private const val LATITUDE = "latitude";
private const val PLATE = "plate";
private const val PLACE_NAME = "place_name";

private const val TABLE_NAME = "postal_codes"

class PostalCodeRepository @Inject constructor() : IPostalCodeRepository {
    @Inject
    lateinit var db: SQLiteDatabase

    private fun createPostalCode(cursor: Cursor) : PostalCode
    {
        return PostalCode().apply {
            id = cursor.getLong(0)
            code = cursor.getInt(1)
            adminCode1 = cursor.getString(2)
            adminCode2 = cursor.getString(3)
            adminName1 = cursor.getString(4)
            adminName2 = cursor.getString(5)
            longitude = cursor.getDouble(6)
            latitude = cursor.getDouble(7)
            plate = cursor.getString(8)
            placeName = cursor.getString(9)
        }
    }

    private fun findByCodeQueryCallback(cursor: Cursor) : MutableIterable<PostalCode>
    {
        val postalCodes = ArrayList<PostalCode>()

        if (!cursor.moveToFirst())
            return postalCodes

        do
            postalCodes.add(createPostalCode(cursor))
        while (cursor.moveToFirst())

        return postalCodes
    }

    override fun findByCode(code: Int) : MutableIterable<PostalCode>
    {
        try {
            db.rawQuery("select * from $TABLE_NAME where code = ?", arrayOf("$code"))
                .use { return findByCodeQueryCallback(it) }
        }
        catch (ex: Throwable) {
            throw ex
        }
    }

    override fun <S : PostalCode?> save(postalCode: S): S
    {
        val cv = ContentValues();

        cv.put(CODE, postalCode?.code)
        cv.put(ADMIN_CODE1, postalCode?.adminCode1)
        cv.put(ADMIN_CODE2, postalCode?.adminCode2)
        cv.put(ADMIN_NAME1, postalCode?.adminName1)
        cv.put(ADMIN_NAME2, postalCode?.adminName2)
        cv.put(LONGITUDE, postalCode?.longitude)
        cv.put(LATITUDE, postalCode?.latitude)
        cv.put(PLATE, postalCode?.plate)
        cv.put(PLACE_NAME, postalCode?.placeName)

        val postalCodeId = db.insert(TABLE_NAME, null, cv);

        if (postalCodeId == -1L)
            throw SQLiteException("PostalCodeRepository.save")

        return postalCode.also { it?.id = postalCodeId }
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