package com.example.tracker_presentation.event

import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_presentation.model.Meal

sealed class TrackerOverviewEvent {

  object onNextDayClick : TrackerOverviewEvent()
  object onPreviousDayClick : TrackerOverviewEvent()

  data class onToggleMealClick(val meal: Meal) :TrackerOverviewEvent()
  data class onDeleteMealClick(val trackedFood: TrackedFood) : TrackerOverviewEvent()
  data class onAddFoodClick(val meal: Meal) : TrackerOverviewEvent()

}
