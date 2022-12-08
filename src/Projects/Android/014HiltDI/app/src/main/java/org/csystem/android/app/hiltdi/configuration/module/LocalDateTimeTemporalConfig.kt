package org.csystem.android.app.hiltdi.configuration.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.time.LocalDateTime
import java.time.temporal.Temporal

@Module
@InstallIn(ActivityComponent::class)
object LocalDateTimeTemporalConfig {
    @Provides
    fun createTemporal() : Temporal = LocalDateTime.now()
}