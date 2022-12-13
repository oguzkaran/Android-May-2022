package org.csystem.android.app.hiltdi.configuration.inject.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import org.csystem.android.app.hiltdi.configuration.inject.annotattion.mapper.DateStringMapperAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotattion.mapper.TimeStringMapperAuthInterceptor
import org.csystem.android.app.hiltdi.data.DateStringMapper
import org.csystem.android.app.hiltdi.data.IMapper
import org.csystem.android.app.hiltdi.data.TimeStringMapper
import java.time.temporal.Temporal

@Module
@InstallIn(ActivityComponent::class)
abstract class MapperConfig {
    @DateStringMapperAuthInterceptor
    @Binds
    abstract fun bindDateStringMapper(dateStringMapper: DateStringMapper) : IMapper<Temporal, String>

    @TimeStringMapperAuthInterceptor
    @Binds
    abstract fun bindTimeStringMapper(timeStringMapper: TimeStringMapper) : IMapper<Temporal, String>
}