package com.example.fitbalance.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.core.data.pref.MyPreferences
import com.example.core.domain.prefrences.Preferences
import com.example.core.domain.usecase.FilterOutDigits
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


  @Provides
  @Singleton
  fun provideSharedPref(app: Application): SharedPreferences {
    return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
  }

  @Provides
  @Singleton
  fun providePreferences(sharedPreferences: SharedPreferences): Preferences {
    return MyPreferences(sharedPreferences)
  }

  @Provides
  @Singleton
  fun provideFilterDigitsUseCase(): FilterOutDigits {
    return FilterOutDigits()
  }


}
