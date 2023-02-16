package com.borasahin.android.library.geonames.postalcode.data.service.mapper.di.module.geonames

import com.borasahin.android.library.geonames.postalcode.data.service.mapper.geonames.IPostalCodeMapper
import com.borasahin.android.library.geonames.postalcode.data.service.mapper.geonames.PostalCodeMapper
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