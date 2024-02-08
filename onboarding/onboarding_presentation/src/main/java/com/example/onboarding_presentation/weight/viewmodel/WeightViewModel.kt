package com.example.onboarding_presentation.weight.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.pref.MyPreferences
import com.example.core.navigation.routes
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
  val preferences: MyPreferences
) : ViewModel() {
  var weight by mutableStateOf("100")
    private set
  var _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()
  fun onHeightChange(newWeight: String) {
    weight = newWeight
  }

  fun onNextClick() {
    viewModelScope.launch {
      val weightNum = weight.toFloatOrNull() ?: kotlin.run {
        _uiEvent.send(
          UiEvent.ShowSnakeBar(UiText.ResourceUiText(com.example.core.R.string.error_height_cant_be_empty))
        )

        return@launch
      }
      preferences.saveWeight(weightNum)
      _uiEvent.send(
        UiEvent.Navigate(route = routes.ACTIVITY_LEVEL)
      )
    }
  }

}
