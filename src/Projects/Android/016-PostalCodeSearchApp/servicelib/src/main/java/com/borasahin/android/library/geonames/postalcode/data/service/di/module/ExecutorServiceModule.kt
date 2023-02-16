package com.borasahin.android.library.geonames.postalcode.data.service.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class ExecutorServiceModule @Inject constructor() {
    @Provides
    fun provideExecutorService() : ExecutorService = Executors.newSingleThreadExecutor()
}