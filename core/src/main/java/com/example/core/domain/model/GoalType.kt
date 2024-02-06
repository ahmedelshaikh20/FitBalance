package com.example.core.domain.model


sealed class GoalType(val goal: String) {
  object LoseWeight : GoalType("lose_weight")
  object GainWeight : GoalType("gain_weight")

  object KeepWeight : GoalType("keep_weight")

  companion object {
    fun fromString(goal: String): GoalType {
      return when (goal) {
        "lose_weight" -> LoseWeight
        "gain_weight" -> GainWeight
        "keep_weight" -> KeepWeight
        else -> KeepWeight
      }

    }
  }
}
