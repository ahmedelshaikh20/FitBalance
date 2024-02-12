package com.example.tracker_domain.usecases

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

class TrackFood @Inject constructor(
  private val repository: TrackerRepository
) {

  suspend operator fun invoke(
    food: TrackableFood,
    amount: Int,
    mealType: MealType,
    date: LocalDate
  ) {
    repository.insertTrackedFood(
      TrackedFood(
        name = food.name,
        imageUrl = food.imageUrl,
        amount = amount,
        mealType = mealType,
        date = date,
        protein = ((food.proteinPer100g / 100f) * amount).roundToInt(),
        fat = ((food.fatPer100g / 100f) * amount).roundToInt(),
        carbs = ((food.carbsPer100g / 100f) * amount).roundToInt(),
        calories = ((food.caloriesPer100g / 100f) * amount).roundToInt(),
      )
    )

  }
}
