package com.example.tracker_presentation.search.screen

import TrackableFoodItem
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.core_ui.LocalSpacing
import com.example.core.R
import com.example.core.util.UiEvent
import com.example.tracker_domain.model.MealType
import com.example.tracker_presentation.search.component.SearchTextField
import com.example.tracker_presentation.search.event.SearchEvent
import com.example.tracker_presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SearchScreen(
  scaffoldState: ScaffoldState,
  mealName: String,
  dayOfMonth: Int,
  month: Int,
  year: Int,
  onNavigateUp: () -> Unit,
  viewModel: SearchViewModel = hiltViewModel()
) {
  val spacing = LocalSpacing.current
  val context = LocalContext.current
  val state = viewModel.state
  val keyboardController = LocalSoftwareKeyboardController.current
  LaunchedEffect(key1 = keyboardController) {
    viewModel.uiEvent.collect { event ->
      when (event) {
        is UiEvent.ShowSnakeBar -> {
          scaffoldState.snackbarHostState.showSnackbar(
            message = event.message.fromString(context)
          )
          keyboardController?.hide()
        }
        is UiEvent.NavigateUp -> onNavigateUp()
        else -> Unit
      }
    }
  }
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(spacing.spaceMedium),
  ) {
    Text(
      text = stringResource(id = R.string.add_meal, mealName),
      style = MaterialTheme.typography.h2
    )
    Spacer(modifier = Modifier.height(spacing.spaceMedium))
    SearchTextField(
      text = state.searchQuery,
      hint = if (!state.isHintVisible) stringResource(id = R.string.search) else "",
      onValueChange = {
        viewModel.onEvent(SearchEvent.onQueryChange(it))
      },
      onSearch = { viewModel.onEvent(SearchEvent.onSearchClick) },
      onFocusChange = { viewModel.onEvent(SearchEvent.onSearchFocusChange(it.isFocused)) })

    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(spacing.spaceMedium)
    ) {
      items(state.trackableFood) { food ->
        TrackableFoodItem(
          trackableFoodUiState = food,
          onClick = { viewModel.onEvent(SearchEvent.onTrackableFoodClick(food.food)) },
          onAmountChange = {
            viewModel.onEvent(
              SearchEvent.onAmountFoodChange(
                trackableFood = food.food,
                amount = it
              )
            )
          },
          onTrack = {
            viewModel.onEvent(
              SearchEvent.onTrackFoodClick(
                food.food,
                MealType.fromString(mealName),
                date = LocalDate.of(year, month, dayOfMonth)
              )
            )
          })



      }

    }
  }

}


