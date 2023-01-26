package com.erbaris.android.library.geonames.postalcode.data.di.hilt.module

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
abstract class PostalCodeModule {
    @Binds
    @Singleton
    abstract fun bindPostalCodeInfoRepository(postalCodeInfoRepository: PostalCodeInfoRepository): IPostalCodeInfoRepository

    @Binds
    @Singleton
    abstract fun bindPostalCodeRepository(postalCodeRepository: PostalCodeRepository) : IPostalCodeRepository
}