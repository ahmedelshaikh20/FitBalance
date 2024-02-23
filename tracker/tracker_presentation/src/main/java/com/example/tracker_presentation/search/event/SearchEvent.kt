package com.example.tracker_presentation.search.event

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {
  data class onQueryChange(val query: String) : SearchEvent()
  object onSearchClick : SearchEvent()
  data class onTrackableFoodClick(val trackableFood: TrackableFood) : SearchEvent()
  data class onAmountFoodChange(val amount: String, val trackableFood: TrackableFood) :
    SearchEvent()


  data class onTrackFoodClick(
    val trackableFood: TrackableFood,
    val mealType: MealType,
    val date: LocalDate
  ) : SearchEvent()

  data class onSearchFocusChange(val isFocused: Boolean) : SearchEvent()

}
