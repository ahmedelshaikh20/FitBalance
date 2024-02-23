package com.example.tracker_presentation.trackeroverview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.R
import com.example.core.util.UiText
import com.example.core_ui.LocalSpacing
import com.example.tracker_domain.model.MealType
import com.example.tracker_presentation.components.UnitDisplay
import com.example.tracker_presentation.model.Meal


@Composable
fun ExpandableMeal(
  meal: Meal,
  onToggleClick: () -> Unit,
  content: @Composable () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column {
    val context = LocalContext.current
    Row(modifier = modifier
      .fillMaxWidth()
      .clickable { onToggleClick() }
      .padding(LocalSpacing.current.spaceMedium), verticalAlignment = Alignment.CenterVertically) {
      Image(painter = painterResource(id = meal.drawableRes), contentDescription = "Meal Image")
      Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
      Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.Center
      ) {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(text = meal.name.fromString(context), style = MaterialTheme.typography.h3)
          Icon(
            imageVector = if (meal.isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
            contentDescription = "Expand Icon"
          )


        }
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {


          UnitDisplay(amount = meal.calories, unit = stringResource(id = R.string.kcal))
          NutrientInfo(
            amount = meal.carbs, name = stringResource(id = R.string.carbs), unit = stringResource(
              id = R.string.grams
            )
          )
          NutrientInfo(
            amount = meal.protein,
            name = stringResource(id = R.string.protein),
            unit = stringResource(
              id = R.string.grams
            )
          )
          NutrientInfo(
            amount = meal.fats, name = stringResource(id = R.string.fat), unit = stringResource(
              id = R.string.grams
            )
          )

        }
      }


    }
    AnimatedVisibility(visible = meal.isExpanded) {
      content()
    }

  }
}


@Preview(showBackground = true)
@Composable
fun PreviewExpandableMeal() {
  ExpandableMeal(
    meal = Meal(
      drawableRes = R.drawable.ic_breakfast,
      mealType = MealType.BreakFast,
      calories = 2000,
      fats = 20,
      carbs = 30,
      protein = 55,
      name = UiText.ResourceUiText(R.string.breakfast)
    ), onToggleClick = { /*TODO*/ }, content = { /*TODO*/ })
}
