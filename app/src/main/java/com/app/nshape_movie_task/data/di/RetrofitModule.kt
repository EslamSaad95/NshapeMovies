package com.app.nshape_movie_task.data.di

import com.app.nshape_movie_task.BuildConfig
import com.app.nshape_movie_task.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

  @Singleton
  @Provides
  fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level =
      HttpLoggingInterceptor.Level.BODY
  }

  @Singleton
  @Provides
  fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
  ) = OkHttpClient.Builder()
    .addInterceptor(Interceptor {

      val original = it.request()
      val url: HttpUrl =
        original.url.newBuilder().addQueryParameter("api_key", BuildConfig.AUTH_TOKEN)
          .build()
      val request = it.request().newBuilder()
        .header("Content-Type", "application/json")
        .url(url)
        .build()

      it.proceed(request)
    })

    .addInterceptor(loggingInterceptor)
    .build()

  @Singleton
  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .build()

  @Singleton
  @Provides
  fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}