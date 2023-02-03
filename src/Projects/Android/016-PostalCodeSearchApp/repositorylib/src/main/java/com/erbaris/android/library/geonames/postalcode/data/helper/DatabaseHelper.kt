package com.erbaris.android.library.geonames.postalcode.data.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.karandev.util.data.repository.exception.RepositoryException
import dagger.hilt.android.qualifiers.ApplicationContext
import java.sql.SQLException
import javax.inject.Inject

private const val DATABASE_NAME = "postalcodeappdb"
private const val DATABASE_VERSION = 2

private const val CREATE_POSTAL_CODE_INFO = """
    CREATE TABLE postal_code_info (
        code INTEGER,
        query_date_time INTEGER not null,
        CONSTRAINT postal_code_info_PK PRIMARY KEY(code)
    );
"""

private const val CREATE_POSTAL_CODES = """
    CREATE TABLE postal_codes (
        postal_code_id INTEGER primary key AUTOINCREMENT,
        code INTEGER not null,
        admin_code1 TEXT,
        longitude REAL not null,
        country_code TEXT,
        admin_name1 TEXT,
        place_name TEXT,
        latitude REAL not null,
        iso_31662_Info TEXT,
        CONSTRAINT postal_codes_FK FOREIGN KEY (code) REFERENCES postal_code_info(code)
    );
"""

private const val DROP_POSTAL_CODE_INFO = "DROP TABLE postal_code_info"
private const val DROP_POSTAL_CODES = "DROP TABLE postal_codes"

class DatabaseHelper @Inject constructor(@ApplicationContext var context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase)
    {
        try {
            db.execSQL(CREATE_POSTAL_CODE_INFO)
            db.execSQL(CREATE_POSTAL_CODES)
        }
        catch (ex: SQLException) {
            throw RepositoryException("DatabaseHelper.onCreate")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int)
    {
        try {
            db.execSQL(DROP_POSTAL_CODES)
            db.execSQL(DROP_POSTAL_CODE_INFO)
            onCreate(db)
        }
        catch (ex: SQLException) {
            throw RepositoryException("DatabaseHelper.onCreate")
        }
    }
}