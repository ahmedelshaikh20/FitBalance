package com.example.core.domain.prefrences

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo

interface Preferences {
  fun saveGender(gender: Gender)

  fun saveAge(age: Int)
  fun saveWeight(weight: Float)
  fun saveHeight(height: Int)
  fun saveActivityLevel(activityLevel: ActivityLevel)

  fun saveGoalType(goalType: GoalType)
  fun saveCarbRatio(ratio: Float)
  fun saveProteinRatio(ratio: Float)
  fun saveFatsRatio(ratio: Float)
  fun loadUserInfo(): UserInfo
  fun shouldShowOnBoarding(shouldShow: Boolean)
  fun loadShouldShowOnBoarding(): Boolean


  companion object {

    const val KEY_AGE = "age"
    const val KEY_GENDER = "age"
    const val KEY_HEIGHT = "height"
    const val KEY_WEIGHT = "weight"
    const val KEY_ACTIVITY_LEVEL = "activity_level"
    const val KEY_GOAL_TYPE = "goal_type"
    const val KEY_CARB_RATIO = "carb_ratio"
    const val KEY_PROTEIN_RATIO = "protein_ratio"
    const val KEY_FAT_RATIO = "fats_ratio"
    const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_on_boarding"

  }
}
