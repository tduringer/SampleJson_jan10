package com.example.samplejson_jan10.di

import com.example.samplejson_jan10.model.network.SampleJsonService
import com.example.samplejson_jan10.model.repository.SampleJsonRepository
import com.example.samplejson_jan10.utils.BASE_URL
import com.example.samplejson_jan10.utils.TIMEOUT
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {

    @Provides
    @Singleton
    fun providesMoshi() = Moshi.Builder().build()

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT,TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT,TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesSampleJsonService(retrofit: Retrofit): SampleJsonService {
        return retrofit.create(SampleJsonService::class.java)
    }

    @Provides
    @Singleton
    fun providesSampleJsonRepository(sampleJsonService: SampleJsonService): SampleJsonRepository {
        return SampleJsonRepository(sampleJsonService)
    }

}