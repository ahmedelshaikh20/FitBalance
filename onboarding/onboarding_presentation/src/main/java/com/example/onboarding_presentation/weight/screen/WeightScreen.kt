package com.example.onboarding_presentation.weight.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
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
import com.example.onboarding_presentation.weight.viewmodel.WeightViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WeightScreen(
  scaffoldState: ScaffoldState,
  onNavigate: (UiEvent.Navigate) -> Unit,
  viewModel: WeightViewModel = hiltViewModel()
) {

  val context = LocalContext.current
  LaunchedEffect(key1 = true) {
    viewModel.uiEvent.collectLatest { event ->
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
      Text(text = stringResource(id = R.string.whats_your_weight),style = MaterialTheme.typography.h3)
      Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
      UnitTextField(value = viewModel.weight, onValueChange = {
        if (it.length <= 5)
          viewModel.onHeightChange(it)
      }, unit = stringResource(id = R.string.kg))
    }
    ActionButton(
      text = stringResource(id = R.string.next),
      isEnabled = true,
      onClick = { viewModel.onNextClick() },
      modifier = Modifier.align(Alignment.BottomEnd)
    )
  }
}
