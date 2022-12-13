package org.csystem.android.app.hiltdi.configuration.inject.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.formatter.LocalDateFormatterAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.formatter.LocalDateTimeFormatterAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.formatter.LocalTimeFormatterAuthInterceptor
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataTimeFormatterConfig {
    @Provides
    @Singleton
    fun getLocalDateTimeFormatter() : DateTimeFormatter
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy kk:mm:ss.n")
    }

    @LocalDateFormatterAuthInterceptor
    @Provides
    @Singleton
    fun getLocalDateFormatter() : DateTimeFormatter
    {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }

    @LocalTimeFormatterAuthInterceptor
    @Provides
    @Singleton
    fun getLocalTimeFormatter() : DateTimeFormatter
    {
        return DateTimeFormatter.ofPattern("kk:mm:ss.n")
    }
}