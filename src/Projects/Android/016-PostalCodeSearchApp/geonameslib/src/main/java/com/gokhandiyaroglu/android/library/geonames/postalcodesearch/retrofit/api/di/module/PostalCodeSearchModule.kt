package com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.api.di.module

import com.gokhandiyaroglu.android.app.geonames.postalcodesearch.api.BASE_URL
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.api.IPostalCodeSearch
import com.karandev.util.retrofit.RetrofitUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PostalCodeSearchModule @Inject constructor() {
    @Provides
    fun providePostalCodeSearch() : IPostalCodeSearch = RetrofitUtil.createRetrofitWithLogging(BASE_URL)
        .create(IPostalCodeSearch::class.java)
}