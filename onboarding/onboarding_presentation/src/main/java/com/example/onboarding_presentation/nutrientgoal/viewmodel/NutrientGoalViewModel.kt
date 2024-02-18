package com.example.onboarding_presentation.nutrientgoal.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.pref.MyPreferences
import com.example.core.domain.usecase.ValidateNutrients
import com.example.core.navigation.routes
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
  val preferences: MyPreferences,
  val validateNutrients: ValidateNutrients
) : ViewModel() {

  var state by mutableStateOf(NutrientGoalState())
    private set

  private var _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()
  fun onEvent(event: NutrientGoalUiEvent) {
    when (event) {
      is NutrientGoalUiEvent.CarbChanged -> {
        if (event.carb.length <= 3)
          state = state.copy(carbRatio = event.carb)
      }

      is NutrientGoalUiEvent.FatsChanged -> {
        if (event.fats.length <= 3)
          state = state.copy(fatRatio = event.fats)

      }

      NutrientGoalUiEvent.OnNextClick -> {

        val validateResult = validateNutrients.invoke(
          carbRatio = state.carbRatio,
          proteinRatio = state.proteinRatio,
          fatsRatio = state.fatRatio
        )
        viewModelScope.launch {
          when (validateResult) {
            is ValidateNutrients.Result.Error -> {
              _uiEvent.send(
                UiEvent.ShowSnakeBar(validateResult.errorMessage)
              )
            }

            is ValidateNutrients.Result.Success -> {
              preferences.saveCarbRatio(validateResult.carbRatio.toFloat())
              preferences.saveProteinRatio(validateResult.proteinRatio.toFloat())
              preferences.saveFatsRatio(validateResult.fatsRatio.toFloat())
              _uiEvent.send(
                UiEvent.Navigate(route = routes.OVERVIEW)
              )
            }
          }
        }
      }

      is NutrientGoalUiEvent.ProteinChange -> {
        if (event.protein.length <= 3)
          state = state.copy(proteinRatio = event.protein)

      }
    }

  }


}
