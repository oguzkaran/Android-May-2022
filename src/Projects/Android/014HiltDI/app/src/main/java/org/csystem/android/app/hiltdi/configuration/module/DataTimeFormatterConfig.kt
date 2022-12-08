package org.csystem.android.app.hiltdi.configuration.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataTimeFormatterConfig {
    @Provides
    @Singleton
    fun getDateTimeFormatter() : DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss.n")
}