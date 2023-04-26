package org.csystem.android.app.upperserver.client.configuration.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors

@Module
@InstallIn(SingletonComponent::class)
object SingleThreadPoolConfig {
    @Provides
    fun singleThreadPool() = Executors.newSingleThreadExecutor()
}