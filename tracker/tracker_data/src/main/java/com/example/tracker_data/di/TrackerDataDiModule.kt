package com.example.tracker_data.di

import android.app.Application
import androidx.room.Room
import com.example.tracker_data.local.TrackerDatabase
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_data.repository.TrackerRepositoryImpl
import com.example.tracker_domain.repository.TrackerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TrackerDataDiModule {

  @Provides
  @Singleton
  fun provideOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
      ).build()
  }

  @Provides
  @Singleton
  fun provideOpenFoodApi(okHttpClient: OkHttpClient): OpenFoodApi {
    return Retrofit.Builder()
      .baseUrl(OpenFoodApi.BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create())
      .build().create()
  }

  @Provides
  @Singleton
  fun provideDatabase(application: Application): TrackerDatabase {
    return Room.databaseBuilder(
      application,
      TrackerDatabase::class.java,
      "tracker_database"
    ).build()
  }

  @Provides
  @Singleton
  fun provideRepository(
    db: TrackerDatabase,
    api: OpenFoodApi
  ): TrackerRepository {
    return TrackerRepositoryImpl(
      dao = db.dao,
      api = api
    )
  }

}
