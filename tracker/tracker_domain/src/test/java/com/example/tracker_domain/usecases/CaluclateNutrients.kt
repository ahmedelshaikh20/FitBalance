package com.example.tracker_domain.usecases

import com.google.common.truth.Truth.assertThat
import com.example.core.data.pref.MyPreferences
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.prefrences.Preferences
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculateNutrients {
  lateinit var calculateMealsNutrients: CalculateMealsNutrients


  @Before
  fun setUp(){
    val pref = mockk<MyPreferences>(relaxed = true)
    every { pref.loadUserInfo()} returns UserInfo(
      gender = Gender.Male ,
      activityLevel = ActivityLevel.Medium,
      goalType = GoalType.KeepWeight,
      age = 20,
      weight = 120f,
      height = 177,
      carbRatio = 0.3f,
      proteinRatio = 0.4f,
      fatsRatio = 0.3f
    )
    calculateMealsNutrients = CalculateMealsNutrients(pref)


  }


  @Test
  fun testCaloriesForBreakfast(){
    val trackedFoods = (1..30).map {
      TrackedFood(
        name = "name",
        carbs = Random.nextInt(100),
        protein = Random.nextInt(100),
        fat = Random.nextInt(100),
        mealType = MealType.fromString(
          listOf("breakfast", "lunch", "dinner", "snack").random()
        ),
        imageUrl = null,
        amount = 100,
        date = LocalDate.now(),
        calories = Random.nextInt(2000)
      )
    }
    val caloriesResult = calculateMealsNutrients(trackedFoods)
    val breakfastCalories = caloriesResult.mealNutrients.values.filter {
      it.mealType == MealType.BreakFast
    }.sumOf {
      it.calories
    }
    val expectedCalories = trackedFoods
      .filter { it.mealType is MealType.BreakFast }
      .sumOf { it.calories }

    assertThat((breakfastCalories)).isEqualTo(expectedCalories)
  }

}
