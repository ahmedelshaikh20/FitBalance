package com.example.tracker_presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.FilterOutDigits
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.usecases.TrackerUseCases
import com.example.core.R
import com.example.tracker_presentation.search.event.SearchEvent
import com.example.tracker_presentation.search.state.SearchState
import com.example.tracker_presentation.search.state.TrackableFoodUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  val trackerUseCases: TrackerUseCases,
  val filterOutDigits: FilterOutDigits
) : ViewModel() {


  var state by mutableStateOf(SearchState())
    private set

  private val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onEvent(event: SearchEvent) {
    when (event) {
      is SearchEvent.onAmountFoodChange -> {
       state = state.copy( trackableFood = state.trackableFood.map {
          if (it.food == event.trackableFood) {
            it.copy(amount = event.amount)
          } else it
        })
      }

      is SearchEvent.onQueryChange -> {
        state = state.copy(
          searchQuery = event.query
        )
      }

      SearchEvent.onSearchClick -> {
        executeSearch()
      }

      is SearchEvent.onSearchFocusChange -> {
        state = state.copy(
          isHintVisible = event.isFocused && state.searchQuery.isBlank()
        )
      }

      is SearchEvent.onTrackFoodClick -> {
        trackFood(event.trackableFood, event.mealType, event.date)
      }

      is SearchEvent.onTrackableFoodClick -> {
        state = state.copy(
          trackableFood = state.trackableFood.map {
            if (it.food == event.trackableFood) {
              it.copy(isExpanded = !it.isExpanded)
            } else it
          })
      }
    }

  }

  private fun executeSearch() {

    viewModelScope.launch {
      state = state.copy(isSearching = true, trackableFood = emptyList())
      val searchResult = trackerUseCases.searchFood(state.searchQuery)

      searchResult.onSuccess { trackableFoodsList ->
        val trackableFoods: List<TrackableFoodUiState> = trackableFoodsList.map {
          TrackableFoodUiState(food = it)
        }

        state = state.copy(trackableFood = trackableFoods, isSearching = false)
      }
        .onFailure {
          state = state.copy(isSearching = false)
          _uiEvent.send(UiEvent.ShowSnakeBar(UiText.ResourceUiText(R.string.error_something_went_wrong)))
        }
    }
  }

  private fun trackFood(trackableFood: TrackableFood, mealType: MealType, date: LocalDate) {
    viewModelScope.launch {
      val foodToTrackState = state.trackableFood.find { it.food == trackableFood }
      trackerUseCases.trackFood(
        food = foodToTrackState?.food ?: return@launch,
        mealType = mealType,
        date = date,
        amount = foodToTrackState.amount.toIntOrNull() ?: return@launch
      )
      _uiEvent.send(UiEvent.NavigateUp)

    }
  }

}
