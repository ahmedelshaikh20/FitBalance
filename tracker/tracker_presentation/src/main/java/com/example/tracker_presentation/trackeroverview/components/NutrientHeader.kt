package com.example.tracker_presentation.trackeroverview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing
import com.example.core.R
import com.example.core_ui.CarbColor
import com.example.core_ui.FatColor
import com.example.core_ui.ProteinColor
import com.example.tracker_presentation.components.UnitDisplay
import com.example.tracker_presentation.event.TrackerOverviewState

@Composable
fun NutrientHeader(
  state: TrackerOverviewState,
  modifier: Modifier = Modifier
) {
  val spacing = LocalSpacing.current
  val animatedCaloriesCount = animateIntAsState(targetValue = state.totalCalories, label = "")
  Column(
    modifier = modifier
      .fillMaxSize()
      .clip(
        RoundedCornerShape(
          bottomStart = 50.dp,
          bottomEnd = 50.dp
        )
      )
      .background(MaterialTheme.colors.primary)
      .padding(
        vertical = spacing.spaceLarge,
        horizontal = spacing.spaceMedium
      )
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      UnitDisplay(
        amount = animatedCaloriesCount.value,
        unit = stringResource(id = R.string.kcal),
        amountColor = MaterialTheme.colors.onPrimary,
        amountTextSize = 40.sp,
        unitColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier.align(Alignment.Bottom)
      )
      Column {
        Text(
          text = stringResource(id = R.string.your_goal),
          style = MaterialTheme.typography.body2,
          color = MaterialTheme.colors.onPrimary
        )
        UnitDisplay(
          amount = animatedCaloriesCount.value,
          unit = stringResource(id = R.string.kcal),
          amountColor = MaterialTheme.colors.onPrimary,
          amountTextSize = 40.sp,
          unitColor = MaterialTheme.colors.onPrimary,
        )
      }
    }
    Spacer(modifier = Modifier.height(spacing.spaceSmall))
    NutrientsBar(
      carbs = state.totalCarbs,
      protein = state.totalProtein,
      fats = state.totalFat,
      calories = state.totalCalories,
      caloriesGoal = state.caloriesGoal,
      modifier = Modifier
        .fillMaxWidth()
        .height(30.dp)
    )
    Spacer(modifier = Modifier.height(spacing.spaceLarge))
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      NutrientBarInfo(
        value = state.totalCarbs,
        goal = state.carbsGoal,
        name = stringResource(id = R.string.carbs),
        color = CarbColor,
        modifier = Modifier.size(90.dp)
      )
      NutrientBarInfo(
        value = state.totalProtein,
        goal = state.proteinGoal,
        name = stringResource(id = R.string.protein),
        color = ProteinColor,
        modifier = Modifier.size(90.dp)
      )
      NutrientBarInfo(
        value = state.totalFat,
        goal = state.fatGoal,
        name = stringResource(id = R.string.fat),
        color = FatColor,
        modifier = Modifier.size(90.dp)
      )
    }


  }
}
