package com.juniorteam.goodfood.di

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.juniorteam.data.constants.ApiConstants
import com.juniorteam.data.source.remote.api.SpoonApi
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val tag = NetworkModule::class.java.simpleName

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(ChuckInterceptor(context))
            .addInterceptor(HeaderInterceptor())
            .build()


//        val request = Request.Builder()
//            .build()
//
//        client.newCall(request).execute().use { response ->
//            if (!response.isSuccessful) throw IOException("Unexpected code $response")
//
//            Log.e(tag, "Quota-Request: ${response.header("X-API-Quota-Request")}")
//            Log.e(tag, "Quota-Used: ${response.header("X-API-Quota-Used")}")
//            Log.e(tag, "Quota-Left: ${response.header("X-API-Quota-Left")}")
//    }

        return client
    }


    @Provides
    @Singleton
    fun provideSpoonApi(spoonRetrofit: Retrofit): SpoonApi =
        spoonRetrofit.create(SpoonApi::class.java)
}
