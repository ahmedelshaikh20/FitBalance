package com.example.tracker_domain.usecases

data class TrackerUseCases(
  val trackFood: TrackFood,
  val searchFood: SearchFood,
  val getsFoodForDate: GetsFoodForDate,
  val deleteTrackedFood: DeleteTrackedFood,
  val calculateMealsNutrients: CalculateMealsNutrients
)

