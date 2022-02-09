package com.juniorteam.football.di


import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import android.content.Context
import com.juniorteam.data.constants.ApiConstants
import com.juniorteam.data.source.remote.api.CarsApi
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient().newBuilder()
            .addInterceptor(ChuckInterceptor(context))
            .build()

    @Provides
    @Singleton
    fun provideCarsApi(vehicleRetrofit: Retrofit): CarsApi =
        vehicleRetrofit.create(CarsApi::class.java)
}