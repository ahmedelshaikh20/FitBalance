package com.example.onboarding_presentation.activitylevel.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.pref.MyPreferences
import com.example.core.domain.model.ActivityLevel
import com.example.core.navigation.routes
import com.example.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(
  val preferences: MyPreferences
) : ViewModel() {
  var activityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
    private set

  var _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onActivityLevelChange(newActivityLevel: ActivityLevel) {
    activityLevel = newActivityLevel
  }


  fun onNextClick() {
    viewModelScope.launch {
      preferences.saveActivityLevel(activityLevel)
      _uiEvent.send(
        UiEvent.Navigate(route = routes.ACTIVITY_LEVEL)
      )
    }
  }
}
