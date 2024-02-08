package com.example.onboarding_presentation.activitylevel.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.core.R
import com.example.core.domain.model.ActivityLevel
import com.example.onboarding_presentation.activitylevel.viewmodel.ActivityLevelViewModel
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ActivityLevelScreen(
  onNavigate: (UiEvent.Navigate) -> Unit,
  viewModel: ActivityLevelViewModel = hiltViewModel()
) {
  LaunchedEffect(key1 = true) {
    viewModel.uiEvent.collectLatest { event ->
      when (event) {
        is UiEvent.Navigate -> {
          onNavigate(event)
        }

        UiEvent.NavigateUp -> Unit
        is UiEvent.ShowSnakeBar -> Unit
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
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = stringResource(id = R.string.whats_your_activity_level),
        style = MaterialTheme.typography.h3
      )
      Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
      ) {
        SelectableButton(
          text = stringResource(id = R.string.low),
          isSelected = if (viewModel.activityLevel is ActivityLevel.Low) true else false,
          color = MaterialTheme.colors.primaryVariant,
          selectedTextColor = Color.White,
          onClick = { viewModel.onActivityLevelChange(ActivityLevel.Low) })
        Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
        SelectableButton(
          text = stringResource(id = R.string.medium),
          isSelected = if (viewModel.activityLevel is ActivityLevel.Medium) true else false,
          color = MaterialTheme.colors.primaryVariant,
          selectedTextColor = Color.White,
          onClick = { viewModel.onActivityLevelChange(ActivityLevel.Medium) })
        Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
        SelectableButton(
          text = stringResource(id = R.string.high),
          isSelected = if (viewModel.activityLevel is ActivityLevel.High) true else false,
          color = MaterialTheme.colors.primaryVariant,
          selectedTextColor = Color.White,
          onClick = { viewModel.onActivityLevelChange(ActivityLevel.High) })

      }

    }
    ActionButton(
      text = stringResource(id = R.string.next),
      onClick = {
        viewModel.onNextClick()
      },
      modifier = Modifier.align(Alignment.BottomEnd),
      isEnabled = true
    )
  }


}
