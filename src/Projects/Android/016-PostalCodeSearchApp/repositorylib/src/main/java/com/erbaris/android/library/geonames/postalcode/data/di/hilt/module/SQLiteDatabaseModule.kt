package com.erbaris.android.library.geonames.postalcode.data.di.hilt.module

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class SQLiteDatabaseModule @Inject constructor(){
    @Provides
    fun provideSQLiteDatabase(sqLiteOpenHelper: SQLiteOpenHelper) : SQLiteDatabase = sqLiteOpenHelper.writableDatabase
}