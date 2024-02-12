package com.example.core.data.pref

import android.content.SharedPreferences
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.prefrences.Preferences
import javax.inject.Inject

class MyPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) :
  Preferences {
  override fun saveGender(gender: Gender) {
    sharedPreferences.edit()
      .putString(Preferences.KEY_GENDER, gender.name)
      .apply()

  }

  override fun saveAge(age: Int) {
    sharedPreferences.edit()
      .putInt(Preferences.KEY_AGE, age)
      .apply()
  }

  override fun saveWeight(weight: Float) {
    sharedPreferences.edit()
      .putFloat(Preferences.KEY_WEIGHT, weight)
      .apply()
  }

  override fun saveHeight(height: Int) {
    sharedPreferences.edit()
      .putInt(Preferences.KEY_HEIGHT, height)
      .apply()
  }

  override fun saveActivityLevel(activityLevel: ActivityLevel) {
    sharedPreferences.edit()
      .putString(Preferences.KEY_ACTIVITY_LEVEL, activityLevel.level)
      .apply()
  }

  override fun saveGoalType(goalType: GoalType) {
    sharedPreferences.edit()
      .putString(Preferences.KEY_GOAL_TYPE, goalType.goal)
      .apply()
  }

  override fun saveCarbRatio(ratio: Float) {
    sharedPreferences.edit()
      .putFloat(Preferences.KEY_CARB_RATIO, ratio)
      .apply()
  }

  override fun saveProteinRatio(ratio: Float) {
    sharedPreferences.edit()
      .putFloat(Preferences.KEY_PROTEIN_RATIO, ratio)
      .apply()
  }

  override fun saveFatsRatio(ratio: Float) {
    sharedPreferences.edit()
      .putFloat(Preferences.KEY_FAT_RATIO, ratio)
      .apply()
  }

  override fun loadUserInfo(): UserInfo {
    return UserInfo(
      gender = Gender.fromString(
        sharedPreferences.getString(Preferences.KEY_GENDER, null) ?: "male"
      ),
      age = sharedPreferences.getInt(Preferences.KEY_AGE, -1),
      height = sharedPreferences.getInt(Preferences.KEY_HEIGHT, -1),
      weight = sharedPreferences.getFloat(Preferences.KEY_WEIGHT, -1.0f),
      activityLevel = ActivityLevel.fromString(
        sharedPreferences.getString(
          Preferences.KEY_ACTIVITY_LEVEL,
          null
        ) ?: "medium"
      ),
      goalType = GoalType.fromString(
        sharedPreferences.getString(Preferences.KEY_GOAL_TYPE, null) ?: GoalType.KeepWeight.goal
      ),
      carbRatio = sharedPreferences.getFloat(Preferences.KEY_CARB_RATIO, -1.0f),
      proteinRatio = sharedPreferences.getFloat(Preferences.KEY_PROTEIN_RATIO, -1.0f),
      fatsRatio = sharedPreferences.getFloat(Preferences.KEY_FAT_RATIO, -1.0f),

      )

  }

  override fun shouldShowOnBoarding(shouldShow: Boolean) {
    sharedPreferences.edit()
      .putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow)
      .apply()
  }

  override fun loadShouldShowOnBoarding(): Boolean {
    return sharedPreferences.getBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, true)
  }
}
