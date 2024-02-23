package com.example.tracker_presentation.trackeroverview.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalSpacing

@Composable
fun AddButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  color: Color = MaterialTheme.colors.primary
) {

  Row(
    modifier = modifier
      .clip(RoundedCornerShape(100f))
      .border(width = 1.dp, shape = RoundedCornerShape(100f), color = color)
      .clickable { onClick() },
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {

    Icon(imageVector = Icons.Default.Add, contentDescription = "add Button", tint = color)
    Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
    Text(
      text = text, style = MaterialTheme.typography.button,
      color = color
    )

  }


}



@Preview(showBackground = true)
@Composable
fun PreviewAddButton() {
  AddButton(text = "Add Meal", onClick = { /*TODO*/ }, modifier = Modifier.padding())
}
