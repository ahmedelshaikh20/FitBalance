package com.example.onboarding_presentation.age.viewmodel

import android.util.Log
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
import com.example.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AgeViewModel @Inject constructor(
  private val preferences: MyPreferences,
  private val filterOutDigits: FilterOutDigits
) : ViewModel() {

  var age by mutableStateOf<String>("5")
    private set

  var _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()
  fun onAgeChange(newAge: String) {
    if (newAge.length <=3){

      age = filterOutDigits.invoke(newAge)}
  }

  fun onNextClick() {
    viewModelScope.launch {

      val ageNumber = age.toIntOrNull() ?: kotlin.run {
        _uiEvent.send(
          UiEvent.ShowSnakeBar(
            UiText.ResourceUiText(
              com.example.core.R.string.error_age_cant_be_empty
            )
          )
        )
        return@launch
      }
      ageNumber.let {
        if (it<3){
            _uiEvent.send(UiEvent.ShowSnakeBar(
              UiText.MessageUIText("Age Should be bigger than 3")
            ))

        } else {
          preferences.saveAge(ageNumber)
          _uiEvent.send(UiEvent.Navigate(route = routes.HEIGHT))
        }

      }
    }
  }


}
