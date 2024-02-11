package com.example.tracker_domain.model

sealed class MealType(val name: String) {
  object BreakFast : MealType("breakfast")
  object Lunch : MealType("lunch")
  object Dinner : MealType("dinner")
  object Snack : MealType("snack")


  companion object {
    fun fromString(name: String): MealType {
      when (name) {
        "breakfast" -> return MealType.BreakFast
        "lunch" -> return MealType.Lunch
        "dinner" -> return MealType.Dinner
        "snack" -> return MealType.Snack
        else -> return MealType.BreakFast
      }
    }
  }


}
