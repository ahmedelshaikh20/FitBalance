package com.example.tracker_presentation.trackeroverview.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.core.R
import com.example.tracker_presentation.event.TrackerOverviewEvent
import com.example.tracker_presentation.trackeroverview.components.AddButton
import com.example.tracker_presentation.trackeroverview.components.DaySelector
import com.example.tracker_presentation.trackeroverview.components.ExpandableMeal
import com.example.tracker_presentation.trackeroverview.components.NutrientHeader
import com.example.tracker_presentation.trackeroverview.components.TrackedFoodItem
import com.example.tracker_presentation.viewmodel.TrackerOverviewViewModel

@Composable
fun TrackerOverviewScreen(
  onNavigate: (UiEvent.Navigate) -> Unit,
  viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
  val spacing = LocalSpacing.current
  val state = viewModel.state
  val context = LocalContext.current
  LaunchedEffect(key1 = true) {
    viewModel.uiEvent.collect { event ->
      when (event) {
        is UiEvent.Navigate -> onNavigate(event)
        else -> Unit
      }
    }
  }
  LazyColumn(
    modifier = Modifier
      .fillMaxSize()
      .padding(bottom = spacing.spaceMedium)
  ) {
    item {
      NutrientHeader(state = state)
      DaySelector(
        onNextClick = { viewModel.onEvent(event = TrackerOverviewEvent.onNextDayClick) },
        onPreviousClick = { viewModel.onEvent(event = TrackerOverviewEvent.onPreviousDayClick) },
        date = state.date,
        modifier = Modifier.fillMaxWidth()
      )
    }
    items(state.meals) { meal ->
      ExpandableMeal(
        meal = meal,
        onToggleClick = { viewModel.onEvent(TrackerOverviewEvent.onToggleMealClick(meal)) },
        content = {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(LocalSpacing.current.spaceSmall)
          ) {
            state.trackedFoods.forEach { food ->
              if (food.mealType.name == meal.mealType.name )
                TrackedFoodItem(
                  trackedFood = food,
                  onDeleteClick = { viewModel.onEvent(TrackerOverviewEvent.onDeleteMealClick(food)) })
            }
            AddButton(
              text = stringResource(id = R.string.add_meal, meal.name.fromString(context)),
              onClick = { viewModel.onEvent(TrackerOverviewEvent.onAddFoodClick(meal)) },
              modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
            )
          }
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(5.dp)
          .clip(RoundedCornerShape(5.dp))
      )

    }
  }
}
