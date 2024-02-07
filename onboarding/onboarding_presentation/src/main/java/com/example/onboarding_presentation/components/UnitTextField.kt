package com.example.onboarding_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing

@Composable
fun UnitTextField(
  value: String,
  onValueChange: (String) -> Unit,
  unit: String,
  modifier: Modifier = Modifier,
  textStyle: TextStyle = TextStyle(
    fontSize = 100.sp,
    color = MaterialTheme.colors.primaryVariant
  )
) {
  var text by remember {
    mutableStateOf(value)
  }
  Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
    BasicTextField(
      value = text, onValueChange = {
        text = it
        onValueChange(it)
      },
      textStyle = textStyle,
      keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number
      ),
      singleLine = true,
      modifier = Modifier
        .width(IntrinsicSize.Min)
        .alignByBaseline()
    )
    Spacer(modifier = modifier.width(LocalSpacing.current.spaceSmall))
    Text(text = unit, modifier = Modifier.alignByBaseline())
  }

}

@Preview(showBackground = true)
@Composable
fun UnitTextFieldPreview() {
  Column {

    UnitTextField(
      "120",
      onValueChange = {},
      unit = "cm")
  }
}
