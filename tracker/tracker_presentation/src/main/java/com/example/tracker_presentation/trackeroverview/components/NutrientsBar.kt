package com.example.tracker_presentation.trackeroverview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import com.example.core_ui.CarbColor
import com.example.core_ui.FatColor
import com.example.core_ui.ProteinColor


@Composable
fun NutrientsBar(
  carbs: Int,
  protein: Int,
  fats: Int,
  calories: Int,
  caloriesGoal: Int,
  modifier: Modifier = Modifier
) {
  val background = MaterialTheme.colors.background
  val caloriesExceedColor = MaterialTheme.colors.error
  val carbWidthRatio = remember {
    Animatable(0f)
  }
  val fatWidthRatio = remember {
    Animatable(0f)
  }
  val proteinWidthRatio = remember {
    Animatable(0f)
  }
  LaunchedEffect(key1 = carbs) {
    carbWidthRatio.animateTo(targetValue = (carbs * 4f) / caloriesGoal)
  }
  LaunchedEffect(key1 = protein) {
    proteinWidthRatio.animateTo(targetValue = (protein * 4f) / caloriesGoal)
  }
  LaunchedEffect(key1 = fats) {
    fatWidthRatio.animateTo(targetValue = (fats * 9f) / caloriesGoal)
  }

  Canvas(modifier = modifier) {
    val carbsWidth = carbWidthRatio.value * size.width
    val proteinWidth = proteinWidthRatio.value * size.width
    val fatWidth = fatWidthRatio.value * size.width

    if (calories <= caloriesGoal) {
      drawRoundRect(
        background,
        size = size,
        cornerRadius = CornerRadius(100f)
      )
      drawRoundRect(
        FatColor,
        size = Size(width = carbsWidth + proteinWidth + fatWidth, height = size.height),
        cornerRadius = CornerRadius(100f)

      )
      drawRoundRect(
        ProteinColor,
        size = Size(width = carbsWidth + proteinWidth, height = size.height),
        cornerRadius = CornerRadius(100f)

      )

      drawRoundRect(
        CarbColor,
        size = Size(width = carbsWidth, height = size.height),
        cornerRadius = CornerRadius(100f)
      )
    } else {
      drawRoundRect(
        caloriesExceedColor,
        size = size,
        cornerRadius = CornerRadius(100f)
      )
    }
  }

}

@Preview(apiLevel = 32)
@Composable
fun PreviewBar() {
  NutrientsBar(carbs = 120, protein = 150, fats = 120, calories = 450, caloriesGoal = 2000)
}
