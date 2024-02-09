package com.example.core.domain.usecase

import com.example.core.util.UiText
import com.example.core.R

class ValidateNutrients {

  operator fun invoke(
    carbRatio: String,
    proteinRatio: String,
    fatsRatio: String
  ): Result {
    val carbNum = carbRatio.toIntOrNull()
    val proteinNum = proteinRatio.toIntOrNull()
    val fatsNum = fatsRatio.toIntOrNull()
    if (carbNum == null || proteinNum == null || fatsNum == null) {
      return Result.Error(errorMessage = UiText.ResourceUiText(R.string.error_invalid_values))
    } else if (carbNum + proteinNum + fatsNum != 100) {
      return Result.Error(errorMessage = UiText.ResourceUiText(R.string.error_not_100_percent))
    } else
      return Result.Success(
        carbRatio,
        proteinRatio,
        fatsRatio
      )
  }

  sealed class Result() {
    data class Success(
      val carbRatio: String,
      val proteinRatio: String,
      val fatsRatio: String
    ) : Result()

    data class Error(
      val errorMessage: UiText,
    ) : Result()
  }

}
