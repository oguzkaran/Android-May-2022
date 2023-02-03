package com.erbaris.android.library.geonames.postalcode.data.di.hilt.module

import android.database.sqlite.SQLiteOpenHelper
import com.erbaris.android.library.geonames.postalcode.data.helper.DatabaseHelper
import com.erbaris.android.library.geonames.postalcode.data.repository.IPostalCodeInfoRepository
import com.erbaris.android.library.geonames.postalcode.data.repository.IPostalCodeRepository
import com.erbaris.android.library.geonames.postalcode.data.repository.PostalCodeInfoRepository
import com.erbaris.android.library.geonames.postalcode.data.repository.PostalCodeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SQLiteOpenHelperModule {
    @Binds
    @Singleton
    abstract fun bindSQLiteOpenHelper(databaseHelper: DatabaseHelper): SQLiteOpenHelper


}