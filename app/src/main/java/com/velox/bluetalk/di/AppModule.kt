package com.velox.bluetalk.di

import android.content.Context
import com.velox.bluetalk.data.chat.BluetoothControllerImpl
import com.velox.bluetalk.domain.chat.BluetoothController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideBluetoothController(@ApplicationContext context: Context):BluetoothController{
        return  BluetoothControllerImpl(context)
    }

}