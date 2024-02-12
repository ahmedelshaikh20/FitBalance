package com.example.tracker_presentation.model

import androidx.annotation.DrawableRes
import com.example.core.util.UiText
import com.example.tracker_domain.model.MealType
import com.example.core.R

//This Only Used in UI Screen
  data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val carbs: Int = 0,
    val protein: Int = 0,
    val fats: Int = 0,
    val calories: Int = 0,
    val mealType: MealType,
    val isExpanded: Boolean = false
  )

  val defaultMeals = listOf(
    Meal(
      name = UiText.ResourceUiText(R.string.breakfast),
      drawableRes = R.drawable.ic_breakfast,
      mealType = MealType.BreakFast
    ),
    Meal(
      name = UiText.ResourceUiText(R.string.lunch),
      drawableRes = R.drawable.ic_lunch,
      mealType = MealType.Lunch
    ),
    Meal(
      name = UiText.ResourceUiText(R.string.dinner),
      drawableRes = R.drawable.ic_dinner,
      mealType = MealType.Dinner
    ),
    Meal(
      name = UiText.ResourceUiText(R.string.snacks),
      drawableRes = R.drawable.ic_snack,
      mealType = MealType.Snack
    ),)

//  companion object {
//    fun Default() {
//      return Meal("")
//    }
//  }


