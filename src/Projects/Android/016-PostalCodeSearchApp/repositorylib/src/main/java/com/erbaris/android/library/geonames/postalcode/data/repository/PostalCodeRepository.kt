package com.erbaris.android.library.geonames.postalcode.data.repository

import android.database.sqlite.SQLiteDatabase
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCode
import java.util.*
import javax.inject.Inject

private const val CREATE_POSTAL_CODE = """
    CREATE TABLE postal_codes (
        postal_code_id INTEGER PRIMARY KEY AUTOINCREMENT,
        code INTEGER not null,
        admin_code_1 TEXT,
        longitude REAL not null,
        country_code TEXT,
        admin_name1 TEXT,
        place_name TEXT,
        latitude REAL not null,
        iso_31662_Info TEXT,
        CONSTRAINT postal_code_PK PRIMARY KEY (postal_code_id),
        CONSTRAINT postal_codes_FK FOREIGN KEY (code) REFERENCES postal_code_info(code)
    );
"""



class PostalCodeRepository @Inject constructor() : IPostalCodeRepository {
    @Inject lateinit var db: SQLiteDatabase

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

    override fun <S : PostalCode?> save(entity: S): S
    {
        TODO("Not yet implemented")
    }

    override fun <S : PostalCode?> saveAll(entities: MutableIterable<S>?): MutableIterable<S>
    {
        TODO("Not yet implemented")
    }

}