package com.example.tracker_presentation.trackeroverview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientInfo(
  amount: Int,
  name:String,
  unit: String,
  modifier: Modifier = Modifier,
  amountTextSize: TextUnit = 20.sp,
  amountColor: Color = MaterialTheme.colors.onBackground,
  unitTextSize: TextUnit = 14.sp,
  unitColor: Color = MaterialTheme.colors.onBackground,
  nameTextStyle: TextStyle = MaterialTheme.typography.body1
) {
  val spacing = LocalSpacing.current
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    UnitDisplay(
      amount ,
      unit
    )
    Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
    Text(
      text = name,
      style = MaterialTheme.typography.h2,
      fontSize = unitTextSize,
      color = unitColor,
    )
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewNutrientInfo() {
NutrientInfo(amount = 50, unit = "g" , name = "protein")
}
