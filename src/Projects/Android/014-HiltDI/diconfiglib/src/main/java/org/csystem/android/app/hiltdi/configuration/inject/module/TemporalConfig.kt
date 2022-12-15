package org.csystem.android.app.hiltdi.configuration.inject.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.LocalDateAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.LocalDateTimeAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.LocalTimeAuthInterceptor
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.Temporal

@Module
@InstallIn(ActivityComponent::class)
object TemporalConfig {
    @LocalDateAuthInterceptor
    @Provides
    fun createLocalDate() : Temporal = LocalDate.now()

    @LocalTimeAuthInterceptor
    @Provides
    fun createLocalTime() : Temporal = LocalTime.now()

    @LocalDateTimeAuthInterceptor
    @Provides
    fun createLocalDateTime() : Temporal = LocalDateTime.now()
}