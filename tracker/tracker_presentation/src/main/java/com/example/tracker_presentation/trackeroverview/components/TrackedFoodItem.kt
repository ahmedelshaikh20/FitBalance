package com.example.tracker_presentation.trackeroverview.components


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
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.core.R
import com.example.core_ui.LocalSpacing
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_presentation.components.UnitDisplay
import java.time.LocalDate


@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackedFoodItem(
  trackedFood: TrackedFood,
  onDeleteClick: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column {
    Row(
      modifier = modifier
        .fillMaxWidth()
        .padding(LocalSpacing.current.spaceMedium), verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = rememberImagePainter(data = trackedFood.imageUrl, builder = {
          fallback(R.drawable.ic_burger)
          error(R.drawable.ic_burger)

        }),
        contentDescription = "Meal Image"
      )
      Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
      Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.Center
      ) {
        Row(
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(text = trackedFood.name, maxLines = 2, style = MaterialTheme.typography.h3)
          Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Delete Icon",
            modifier = Modifier.clickable { onDeleteClick() }
          )


        }
        Row(
          modifier = Modifier.fillMaxWidth(),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {


          UnitDisplay(amount = trackedFood.calories, unit = stringResource(id = R.string.kcal))
          NutrientInfo(
            amount = trackedFood.carbs,
            name = stringResource(id = R.string.carbs),
            unit = stringResource(
              id = R.string.grams
            )
          )
          NutrientInfo(
            amount = trackedFood.protein,
            name = stringResource(id = R.string.protein),
            unit = stringResource(
              id = R.string.grams
            )
          )
          NutrientInfo(
            amount = trackedFood.fat,
            name = stringResource(id = R.string.fat),
            unit = stringResource(
              id = R.string.grams
            )
          )

        }
      }


    }


  }
}


@Preview(showBackground = true)
@Composable
fun PreviewTrackedFoodItem() {
  TrackedFoodItem(
    TrackedFood(
      name = "Burger",
      carbs = 120,
      amount = 200,
      calories = 2000,
      date = LocalDate.now(),
      protein = 120,
      fat = 120,
      imageUrl = "http//hdw.com",
      mealType = MealType.BreakFast
    ), onDeleteClick = {})
}
