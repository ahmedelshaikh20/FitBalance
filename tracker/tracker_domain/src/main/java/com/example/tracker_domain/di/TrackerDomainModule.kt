package com.example.tracker_domain.di

import com.example.core.data.pref.MyPreferences
import com.example.tracker_domain.repository.TrackerRepository
import com.example.tracker_domain.usecases.CalculateMealsNutrients
import com.example.tracker_domain.usecases.DeleteTrackedFood
import com.example.tracker_domain.usecases.GetsFoodForDate
import com.example.tracker_domain.usecases.SearchFood
import com.example.tracker_domain.usecases.TrackFood
import com.example.tracker_domain.usecases.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

  @ViewModelScoped
  @Provides
  fun provideUseCases(preferences: MyPreferences, repository: TrackerRepository): TrackerUseCases {
    return TrackerUseCases(
      calculateMealsNutrients = CalculateMealsNutrients(preferences),
      deleteTrackedFood = DeleteTrackedFood(repository),
      searchFood = SearchFood(repository),
      getsFoodForDate = GetsFoodForDate(repository),
      trackFood = TrackFood(repository)
    )
  }

}
