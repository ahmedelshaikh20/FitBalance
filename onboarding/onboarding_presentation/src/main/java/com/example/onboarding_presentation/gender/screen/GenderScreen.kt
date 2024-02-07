package com.example.onboarding_presentation.gender.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.domain.model.Gender
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.R
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.SelectableButton
import com.example.onboarding_presentation.gender.viewmodel.GenderViewModel

@Composable
fun GenderScreen(
  onNavigate: (UiEvent.Navigate) -> Unit,
  genderViewModel: GenderViewModel = hiltViewModel()
) {
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
        text = stringResource(id = com.example.core.R.string.whats_your_gender),
        style = MaterialTheme.typography.h3
      )
      Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
      Row(horizontalArrangement = Arrangement.SpaceEvenly) {
        SelectableButton(
          text = stringResource(id = com.example.core.R.string.male),
          isSelected = genderViewModel.selectedGender is Gender.Male,
          color = MaterialTheme.colors.primaryVariant,
          selectedTextColor = Color.White,
          onClick = { genderViewModel.onGenderSelect(Gender.Male) })
        Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
        SelectableButton(
          text = stringResource(id = com.example.core.R.string.female),
          isSelected = genderViewModel.selectedGender is Gender.Female,
          color = MaterialTheme.colors.primaryVariant,
          selectedTextColor = Color.White,
          onClick = { genderViewModel.onGenderSelect(Gender.Female) })
      }
    }
    ActionButton(text = "Next", onClick = { genderViewModel.onNextClick() } , modifier = Modifier.align(Alignment.BottomEnd) , isEnabled = true
    )
  }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  Column {

    GenderScreen(onNavigate = {})
  }
}
