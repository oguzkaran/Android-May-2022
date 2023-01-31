package com.borasahin.android.library.geonames.postalcode.data.service.mapper.di.module

import com.borasahin.android.library.geonames.postalcode.data.service.mapper.IPostalCodeMapper
import com.borasahin.android.library.geonames.postalcode.data.service.mapper.PostalCodeMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PostalCodeMapperModule {
    @Binds
    abstract fun bindPostalCodeMapper(postalCodeMapper: PostalCodeMapper) : IPostalCodeMapper
}