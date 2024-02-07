package com.example.onboarding_presentation.gender.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.pref.MyPreferences
import com.example.core.domain.model.Gender
import com.example.core.navigation.routes
import com.example.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
  val preferences: MyPreferences
) : ViewModel() {

  var selectedGender by mutableStateOf<Gender>(Gender.Male)
    private set

  // Cahneel used to send one time event( It will only considered one )
  val _uiEvent = Channel<UiEvent>()
  val uiEvent = _uiEvent.receiveAsFlow()

  fun onGenderSelect(gender: Gender) {
    selectedGender = gender
  }

  fun onNextClick(){
    viewModelScope.launch {
     preferences.saveGender(selectedGender)
    _uiEvent.send(UiEvent.Navigate(route = routes.AGE))}
  }


}
