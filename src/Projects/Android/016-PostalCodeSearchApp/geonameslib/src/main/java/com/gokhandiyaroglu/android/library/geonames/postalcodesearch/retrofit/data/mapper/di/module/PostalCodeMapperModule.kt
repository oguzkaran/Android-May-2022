package com.gokhandiyaroglu.android.app.geonames.postalcodesearch.data.mapper.di.module

import com.gokhandiyaroglu.android.app.geonames.postalcodesearch.data.mapper.IPostalCodeMapper
import com.gokhandiyaroglu.android.app.geonames.postalcodesearch.data.mapper.PostalCodeMapper
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