package com.example.onboarding_presentation.age.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.domain.model.Gender
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.age.viewmodel.AgeViewModel
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.SelectableButton
import com.example.onboarding_presentation.components.UnitTextField
import com.example.onboarding_presentation.gender.screen.GenderScreen
import kotlinx.coroutines.flow.collectLatest


@Composable
fun AgeScreen(
  scaffoldState: ScaffoldState,
  onNavigate: (UiEvent.Navigate) -> Unit,
  ageViewModel: AgeViewModel = hiltViewModel()
) {
  val context = LocalContext.current
  LaunchedEffect(key1 = true) {
    ageViewModel.uiEvent.collectLatest { uiEvent ->
      when (uiEvent) {
        is UiEvent.Navigate -> {
          onNavigate(uiEvent)
        }

        UiEvent.NavigateUp -> Unit
        is UiEvent.ShowSnakeBar -> {
          scaffoldState.snackbarHostState.showSnackbar(
            message = uiEvent.message.fromString(context)
          )
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
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = stringResource(id = R.string.whats_your_age),
        style = MaterialTheme.typography.h3
      )
      Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
      UnitTextField(value = ageViewModel.age, onValueChange = {
        if(it.length <=3){
        ageViewModel.onAgeChange(it)}
      }, unit = stringResource(id = R.string.years) ,
        modifier = Modifier.width(IntrinsicSize.Min))

    }
    ActionButton(
      text = "Next",
      onClick = { ageViewModel.onNextClick() },
      modifier = Modifier.align(
        Alignment.BottomEnd
      ),
      isEnabled = true
    )
  }


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  Column {

  }
}
