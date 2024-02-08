package com.example.onboarding_presentation.height.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.UnitTextField
import com.example.onboarding_presentation.height.viewmodel.HeightViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HeightScreen(
  scaffoldState: ScaffoldState,
  onNavigate: (UiEvent.Navigate) -> Unit,
  heightViewModel: HeightViewModel = hiltViewModel()
) {
  val context = LocalContext.current
  LaunchedEffect(key1 = true) {
    heightViewModel.uiEvent.collectLatest { event ->
      when (event) {
        is UiEvent.Navigate -> onNavigate(event)
        UiEvent.NavigateUp -> Unit
        is UiEvent.ShowSnakeBar -> {
          scaffoldState.snackbarHostState.showSnackbar(event.message.fromString(context))
        }
      }
    }

  }


  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(LocalSpacing.current.spaceLarge)
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = stringResource(id = R.string.whats_your_height))
      UnitTextField(value = heightViewModel.height, onValueChange = {
        if (it.length <= 3)
          heightViewModel.onHeightChange(it)
      }, unit = stringResource(id = R.string.cm))
    }
    ActionButton(
      text = stringResource(id = R.string.next),
      isEnabled = true,
      onClick = { heightViewModel.onNextClick() },
      modifier = Modifier.align(Alignment.BottomEnd)
    )
  }
}
