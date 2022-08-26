package com.zemoga.postviewer.core

import com.zemoga.postviewer.data.DataModule
import com.zemoga.postviewer.data.network.PostApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DataModule.returnEnvironment().baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providePostApiClient(retrofit: Retrofit):PostApiClient{
        return retrofit.create(PostApiClient::class.java)
    }


}