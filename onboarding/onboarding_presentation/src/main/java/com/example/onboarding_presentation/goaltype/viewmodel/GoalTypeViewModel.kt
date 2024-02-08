package com.example.onboarding_presentation.goaltype.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.pref.MyPreferences
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.GoalType
import com.example.core.navigation.routes
import com.example.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalTypeViewModel @Inject constructor(
  val preferences: MyPreferences
) : ViewModel() {
  var goalType by mutableStateOf<GoalType>(GoalType.KeepWeight)
    private set

  var _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onGoalTypeChange(newGoalType: GoalType) {
    goalType = newGoalType
  }


  fun onNextClick() {
    viewModelScope.launch {
      preferences.saveGoalType(goalType)
      _uiEvent.send(
        UiEvent.Navigate(route = routes.GOAL)
      )
    }
  }
}
