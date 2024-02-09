package com.example.onboarding_presentation.nutrientgoal.viewmodel

sealed class NutrientGoalUiEvent {
  data class CarbChanged(val carb: String) : NutrientGoalUiEvent()
  data class ProteinChange(val protein: String) : NutrientGoalUiEvent()
  data class FatsChanged(val fats: String) : NutrientGoalUiEvent()
  object OnNextClick : NutrientGoalUiEvent()
}
