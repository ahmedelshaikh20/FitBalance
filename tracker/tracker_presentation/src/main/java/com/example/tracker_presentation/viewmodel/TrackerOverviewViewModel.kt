package com.example.tracker_presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.pref.MyPreferences
import com.example.core.util.UiEvent
import com.example.tracker_domain.usecases.TrackerUseCases
import com.example.tracker_presentation.event.TrackerOverviewEvent
import com.example.tracker_presentation.event.TrackerOverviewState
import com.plcoding.core.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
  preferences: MyPreferences,
  private val trackerUseCases: TrackerUseCases
) : ViewModel() {

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  var state by mutableStateOf(TrackerOverviewState())
  private var getFoodForDateJob: Job? = null

  init {
    // To Not to show onBoarding Process Again
    preferences.shouldShowOnBoarding(false)
  }


  fun onEvent(event: TrackerOverviewEvent) {
    when (event) {
      is TrackerOverviewEvent.onAddFoodClick -> {
        viewModelScope.launch {
          _uiEvent.send(
            UiEvent.Navigate(
              route = Route.SEARCH
                + "/${event.meal.mealType.name}"
                + "/${state.date.dayOfMonth}"
                + "/${state.date.monthValue}"
                + "/${state.date.year}"
            )
          )
        }

      }

      is TrackerOverviewEvent.onDeleteMealClick -> {
        viewModelScope.launch {
          trackerUseCases.deleteTrackedFood(event.trackedFood)
          refreshFood()
        }
      }

      TrackerOverviewEvent.onNextDayClick -> {
        state = state.copy(
          date = state.date.plusDays(1)
        )
        viewModelScope.launch {
          refreshFood()
        }
      }

      TrackerOverviewEvent.onPreviousDayClick -> {
        state = state.copy(
          date = state.date.minusDays(1)
        )
        viewModelScope.launch {
          refreshFood()
        }
      }

      is TrackerOverviewEvent.onToggleMealClick -> {
        state = state.copy(
          meals = state.meals.map {
            if (it.name == event.meal.name) {
              it.copy(isExpanded = !it.isExpanded)
            } else it
          }
        )
      }
    }
  }


  private suspend fun refreshFood() {
    getFoodForDateJob?.cancel()
    getFoodForDateJob = trackerUseCases.getsFoodForDate(state.date)
      .onEach { foods ->
        val nutrientResult = trackerUseCases.calculateMealsNutrients(
          foods
        )
        state = state.copy(
          totalCalories = nutrientResult.totalCalories,
          totalCarbs = nutrientResult.totalCarb,
          totalFat = nutrientResult.totalFat,
          totalProtein = nutrientResult.totalProtein,
          carbsGoal = nutrientResult.carbGoal,
          proteinGoal = nutrientResult.proteinGoal,
          caloriesGoal = nutrientResult.caloriesGoal,
          fatGoal = nutrientResult.fatGoal,
          trackedFoods = foods,
          meals = state.meals.map {
            val nutrientsForMeal = nutrientResult.mealNutrients[it.mealType]
              ?: return@map it.copy(
                carbs = 0,
                protein = 0,
                calories = 0,
                fats = 0
              )

            it.copy(
              carbs = nutrientsForMeal.carbs,
              calories = nutrientsForMeal.calories,
              protein = nutrientsForMeal.protein,
              fats = nutrientsForMeal.fat
            )

          }
        )
      }
      .launchIn(viewModelScope)

  }


}
