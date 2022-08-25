package com.zemoga.postviewer.core

import com.zemoga.postviewer.data.DataModule
import com.zemoga.postviewer.data.environments.Environment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitHelper {

    fun getRetroFit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(DataModule.returnBaseUrl().baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}