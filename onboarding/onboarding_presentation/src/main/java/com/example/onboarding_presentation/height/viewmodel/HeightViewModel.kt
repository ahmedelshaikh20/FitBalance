package com.example.onboarding_presentation.height.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.pref.MyPreferences
import com.example.core.domain.usecase.FilterOutDigits
import com.example.core.navigation.routes
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
  val preferences: MyPreferences,
  val filterOutDigits: FilterOutDigits
) : ViewModel() {
  var height by mutableStateOf("100")
    private set
  var _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()
  fun onHeightChange(value: String) {
    height = filterOutDigits.invoke(value)
  }
  fun onNextClick() {
    viewModelScope.launch {
      val heightNum = height.toIntOrNull() ?: kotlin.run {
        _uiEvent.send(
          UiEvent.ShowSnakeBar(UiText.ResourceUiText(com.example.core.R.string.error_height_cant_be_empty))
        )

        return@launch
      }
      preferences.saveHeight(heightNum)
      _uiEvent.send(
        UiEvent.Navigate(route = routes.WEIGHT)
      )
    }
  }

}
