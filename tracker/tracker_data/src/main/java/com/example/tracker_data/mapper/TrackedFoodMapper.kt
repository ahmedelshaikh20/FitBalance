package com.example.tracker_data.mapper

import com.example.tracker_data.local.entity.TrackedFoodEntity
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import java.time.LocalDate


fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
  return TrackedFood(
    name = name,
    amount = amount,
    calories = calories,
    carbs = carbs,
    protein = protein,
    fat = fat,
    date = LocalDate.of(year, month, dayOfMonth),
    imageUrl = imageUrl,
    mealType = MealType.fromString(type),
    id = id
  )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
  return TrackedFoodEntity(
    name = name,
    amount = amount,
    calories = calories,
    carbs = carbs,
    protein = protein,
    fat = fat,
    dayOfMonth = date.dayOfMonth,
    month = date.monthValue,
    year = date.year,
    imageUrl = imageUrl,
    type = mealType.name,
    id = id
  )
}
