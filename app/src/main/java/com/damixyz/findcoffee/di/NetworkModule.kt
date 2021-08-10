package com.damixyz.findcoffee.di

import com.damixyz.data.api.FourSquareApi
import com.damixyz.findcoffee.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Named("loggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)
    }

    @Provides
    @Named("authInterceptor")
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("client_id", BuildConfig.CLIENT_ID)
                .addQueryParameter("client_secret", BuildConfig.CLIENT_SECRET)
                .build()

            val newRequest = chain.request().newBuilder()
                .url(url = url)
                .build()

            chain.proceed(newRequest)
        }
    }


    @Provides
    fun provideOkHttpClient(
        @Named("authInterceptor") authInterceptor: Interceptor,
        @Named("loggingInterceptor") loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    }

    @Provides
    fun provideApiService(retrofit: Retrofit): FourSquareApi {
        return retrofit.create(FourSquareApi::class.java)
    }
}