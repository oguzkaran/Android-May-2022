package org.csystem.android.app.veterinarian.api.di.module

import com.karandev.util.retrofit.RetrofitUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.csystem.android.app.veterinarian.api.GET_SERVICE_BASE_URL
import org.csystem.android.app.veterinarian.api.IVeterinarianService
import org.csystem.android.app.veterinarian.api.POST_SERVICE_BASE_URL
import org.csystem.android.app.veterinarian.api.di.annotation.VeterinarianGetServiceInterceptor
import org.csystem.android.app.veterinarian.api.di.annotation.VeterinarianPostServiceInterceptor

@Module
@InstallIn(SingletonComponent::class)
object VeterinarianServiceProviderModule {
    @VeterinarianGetServiceInterceptor
    @Provides
    fun provideVeterinarianGetService() : IVeterinarianService
    {
        return RetrofitUtil.createRetrofit(GET_SERVICE_BASE_URL).create(IVeterinarianService::class.java)
    }

    @VeterinarianPostServiceInterceptor
    @Provides
    fun provideVeterinarianPostService() : IVeterinarianService
    {
        return RetrofitUtil.createRetrofit(POST_SERVICE_BASE_URL).create(IVeterinarianService::class.java)
    }
}