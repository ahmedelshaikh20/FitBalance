package com.example.core.domain.model


sealed class ActivityLevel(val level: String) {
  object High : ActivityLevel("high")
  object Medium : ActivityLevel("medium")

  object Low : ActivityLevel("low")

  companion object {
    fun fromString(level: String): ActivityLevel {
      return when (level) {
        "high" -> High
        "medium" -> Medium
        "low" -> Low
        else -> Medium
      }

    }
  }
}
