package com.borasahin.android.library.geonames.postalcode.data.service.di.module

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.borasahin.android.library.geonames.postalcode.data.service.FailHandlerObject
import com.borasahin.android.library.geonames.postalcode.data.service.FindPostalCodeByCodeResultHandlerObject
import com.borasahin.android.library.geonames.postalcode.data.service.SavePostalCodeResultHandlerObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PostalCodeAppServiceHandlerModule @Inject constructor() {
    private fun savePostalCodeResultBlockCallback(msg: Message)
    {
        val ho = msg.obj as SavePostalCodeResultHandlerObject;

        ho.resultBlock(ho.result)
    }

    private fun savePostalCodeFailBlockCallback(msg: Message)
    {
        val ho = msg.obj as FailHandlerObject;

        ho.failBlock(ho.ex)
    }

    private fun findPostalCodeByCodeResultBlockCallback(msg: Message)
    {
        val ho = msg.obj as FindPostalCodeByCodeResultHandlerObject

        ho.resultBlock(ho.result)
    }

    private fun findPostalCodeByCodeFailBlockCallback(msg: Message)
    {
        val ho = msg.obj as FailHandlerObject;

        ho.failBlock(ho.ex)
    }

    @Provides
    fun provideHandler() : Handler = object: Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message)
        {
            when (msg.what) {
                1 -> savePostalCodeResultBlockCallback(msg)
                2 -> savePostalCodeFailBlockCallback(msg)
                3 -> findPostalCodeByCodeResultBlockCallback(msg)
                4 -> findPostalCodeByCodeFailBlockCallback(msg)
            }

            var ho = msg.obj as SavePostalCodeResultHandlerObject
        }
    }
}